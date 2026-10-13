package frc.robot.lib;

/**
 * Limits how fast the robot's 2D velocity can change.
 * <p>
 * The change in velocity is split into a part along the direction of travel and a part across
 * it. Speeding up, braking and turning each have their own limit:
 * <pre>
 *                    ^ cross (limited by lateralLimit)
 *                    |
 *   &lt;-- decelLimit --+-- accelLimit --&gt;   along current velocity
 * </pre>
 * Reversing direction brakes at {@code decelLimit} down to 0, then speeds up at
 * {@code accelLimit}. Read the result with {@link #getX()} and {@link #getY()}.
 */
public final class TranslationRateLimiter {

    private double accelLimit;
    private double decelLimit;
    private double lateralLimit;

    /** The current limited velocity. */
    private double valueX;
    private double valueY;

    /** Below this speed (m/s) there's no direction of travel, so only the accel limit is used. */
    private static final double DIRECTION_EPSILON = 1e-4;

    /**
     * @param accelLimit   max acceleration when speeding up (m/s^2)
     * @param decelLimit   max deceleration when braking (m/s^2)
     * @param lateralLimit max acceleration when changing direction (m/s^2)
     */
    public TranslationRateLimiter(double accelLimit, double decelLimit, double lateralLimit) {
        setLimits(accelLimit, decelLimit, lateralLimit);
    }

    public void setLimits(double accelLimit, double decelLimit, double lateralLimit) {
        this.accelLimit = accelLimit;
        this.decelLimit = decelLimit;
        this.lateralLimit = lateralLimit;
    }

    /**
     * Moves one step toward the requested velocity.
     *
     * @param targetX requested X velocity
     * @param targetY requested Y velocity
     * @param dt      time step (s)
     */
    public void calculate(double targetX, double targetY, double dt) {
        double deltaX = targetX - valueX;
        double deltaY = targetY - valueY;

        double speed = Math.sqrt(valueX * valueX + valueY * valueY);

        if (speed < DIRECTION_EPSILON) {
            // Starting from a stop: just limit the step by the acceleration limit
            double deltaMagnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double limit = accelLimit * dt;
            if (deltaMagnitude > limit) {
                double scale = limit / deltaMagnitude;
                deltaX *= scale;
                deltaY *= scale;
            }
        } else {
            // Unit vector along the current direction of travel.
            double unitX = valueX / speed;
            double unitY = valueY / speed;

            // Split the step into along and across the direction of travel
            double along = deltaX * unitX + deltaY * unitY;
            double cross = deltaY * unitX - deltaX * unitY;

            // Speeding up uses accelLimit, slowing down uses decelLimit
            double alongLimit = (along >= 0 ? accelLimit : decelLimit) * dt;
            double crossLimit = lateralLimit * dt;

            // Braking and turning share the same grip, so limit them together (an ellipse):
            //     (along/alongLimit)^2 + (cross/crossLimit)^2 <= 1
            // If the request is too big, scale both parts down by the same amount.
            double alongRatio = along / alongLimit;
            double crossRatio = cross / crossLimit;
            double demand = alongRatio * alongRatio + crossRatio * crossRatio;

            // A 0/0 ratio gives NaN, which fails this check and leaves a zero step as is
            if (demand > 1) {
                double scale = 1 / Math.sqrt(demand);
                along *= scale;
                cross *= scale;
            }

            // Convert the step back to X/Y
            deltaX = along * unitX - cross * unitY;
            deltaY = along * unitY + cross * unitX;
        }

        valueX += deltaX;
        valueY += deltaY;
    }

    public double getX() {
        return valueX;
    }

    public double getY() {
        return valueY;
    }

    /** Sets the current velocity. Call on every mode change with the robot's measured velocity. */
    public void reset(double x, double y) {
        valueX = x;
        valueY = y;
    }
}
