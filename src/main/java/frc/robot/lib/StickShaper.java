package frc.robot.lib;

import edu.wpi.first.math.MathUtil;

/**
 * Shapes raw joystick input: a deadband, then an expo curve.
 * <p>
 * The expo curve is {@code k*x^3 + (1-k)*x}. It makes the middle of the stick softer for fine
 * control, while full stick still gives full output. k = 0 is linear, k = 1 is cubic.
 * <p>
 * For a 2D stick, use {@link #calculate(double, double)} and read the result with
 * {@link #getX()} and {@link #getY()}. For a single axis, use {@link #calculate(double)}.
 */
public final class StickShaper {

    private final double deadband;
    private final double expo;

    // Output of calculate(x, y)
    private double x;
    private double y;

    /**
     * @param deadband stick values below this are 0 (0 to 1)
     * @param expo     expo amount, 0 = linear, 1 = cubic
     */
    public StickShaper(double deadband, double expo) {
        this.deadband = deadband;
        this.expo = expo;
    }

    /**
     * Shapes a 2D stick. The deadband is a circle, so it works the same in every direction, and
     * the output starts smoothly from 0 at the edge of the deadband.
     */
    public void calculate(double rawX, double rawY) {
        double magnitude = Math.hypot(rawX, rawY);

        if (magnitude < deadband) {
            x = 0;
            y = 0;
            return;
        }

        // Rescale so the deadband edge is 0 and full stick is 1. Corners can read a bit over 1.
        double rescaled = (magnitude - deadband) / (1 - deadband);
        if (rescaled > 1) {
            rescaled = 1;
        }

        double shaped = expo(rescaled, expo);

        // Keep the original stick direction
        x = rawX / magnitude * shaped;
        y = rawY / magnitude * shaped;
    }

    /** Shapes a single axis. Inside the deadband the result is exactly 0. */
    public double calculate(double raw) {
        return expo(MathUtil.applyDeadband(raw, deadband), expo);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private static double expo(double value, double k) {
        return k * value * value * value + (1 - k) * value;
    }
}
