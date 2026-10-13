package frc.robot.lib;

import edu.wpi.first.math.trajectory.TrapezoidProfile;

/**
 * A profiled PID controller that uses the square root of the error. This gives a strong
 * response to small errors and a gentler one to big errors.
 */
public class SqrtErrorProfiledPIDController {
    private double kP, kI, kD;
    private final TrapezoidProfile.Constraints constraints;

    private TrapezoidProfile.State goal;
    private TrapezoidProfile.State setpoint;

    private double messure;

    private double posTolerance;
    private double velTolerance;

    private double totalError;
    private double prevModifiedError;

    private double lastTimestamp;

    /** @param constraints max velocity and acceleration */
    public SqrtErrorProfiledPIDController(double kP, double kI, double kD, TrapezoidProfile.Constraints constraints) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.constraints = constraints;

        this.goal = new TrapezoidProfile.State(0, 0);
        this.setpoint = new TrapezoidProfile.State(0, 0);

        this.posTolerance = 0.0;
        this.velTolerance = 0.0;

        this.totalError = 0;
        this.prevModifiedError = 0;
        this.lastTimestamp = System.currentTimeMillis() / 1000.0;
    }

    public void setPID(double kp, double ki, double kd) {
        this.kP = kp;
        this.kI = ki;
        this.kD = kd;
    }

    public double getError() {
        return Math.abs(goal.position - messure);
    }

    /** Sets the goal position, with zero velocity. */
    public void setGoal(double goalPosition) {
        this.goal = new TrapezoidProfile.State(goalPosition, 0);
    }

    public void setGoal(TrapezoidProfile.State goal) {
        this.goal = goal;
    }

    public TrapezoidProfile.State getGoal() {
        return goal;
    }

    public TrapezoidProfile.State getSetpoint() {
        return setpoint;
    }

    public void setTolerance(double posTolerance, double velTolerance) {
        this.posTolerance = posTolerance;
        this.velTolerance = velTolerance;
    }

    /** Resets the controller to the given position and velocity. */
    public void reset(TrapezoidProfile.State initialState) {
        this.setpoint = new TrapezoidProfile.State(initialState.position, initialState.velocity);
        this.totalError = 0;
        this.prevModifiedError = 0;
        this.lastTimestamp = System.currentTimeMillis() / 1000.0;
    }

    /** True when the profile has reached the goal (within tolerance). */
    public boolean atGoal() {
        return Math.abs(goal.position - setpoint.position) < posTolerance &&
            Math.abs(goal.velocity - setpoint.velocity) < velTolerance;
    }

    /** Steps the motion profile and returns the PID output for the given measurement. */
    public double calculate(double measurement) {
        messure = measurement;
        double now = System.currentTimeMillis() / 1000.0;
        double dt = now - lastTimestamp;
        if (dt <= 0) {
            dt = 0.001; // avoid dividing by 0
        }
        lastTimestamp = now;

        // Next step of the motion profile
        TrapezoidProfile profile = new TrapezoidProfile(constraints);
        setpoint = profile.calculate(dt, setpoint, goal);

        // PID on the square root of the error (keeping its sign)
        double error = setpoint.position - measurement;
        double modifiedError = Math.signum(error) * Math.sqrt(Math.abs(error));

        totalError += modifiedError * dt;
        double derivative = (modifiedError - prevModifiedError) / dt;
        prevModifiedError = modifiedError;

        double pidOutput = kP * modifiedError + kI * totalError + kD * derivative;

        // Add the profile velocity as feedforward
        return Math.abs(error) < posTolerance ? 0 : (pidOutput + setpoint.velocity);
    }
}
