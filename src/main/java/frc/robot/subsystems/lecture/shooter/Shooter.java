package frc.robot.subsystems.lecture.shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class Shooter extends SubsystemBase {

    private final ShooterIO.ShooterOutputs shooterInputs;
    private final ShooterIO shooterIO;

    private double targetSpeed;

    public enum WantedState {
        IDLE,
        MOVE_BY_SPEED,
        DISABLE
    }

    public enum SystemState {
        IDLING,
        MOVING_BY_SPEED,
        DISABLED
    }

    private WantedState wantedState;
    private SystemState systemState;
    private SystemState previousSystemState;

    public Shooter(ShooterIO shooterIO) {
        this.shooterInputs = new ShooterIO.ShooterOutputs();
        this.shooterIO = shooterIO;

        this.wantedState = WantedState.IDLE;
        this.systemState = SystemState.IDLING;
        this.previousSystemState = SystemState.IDLING;

        this.shooterIO.updateOutputs(shooterInputs);
    }

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public WantedState getWantedState() {
        return wantedState;
    }

    public SystemState getSystemState() {
        return systemState;
    }

    public void setTargetSpeed(double targetSpeed) {
        this.targetSpeed = targetSpeed;
    }

    @Override
    public void periodic() {
        shooterIO.updateOutputs(shooterInputs);

        systemState = handleStateTransition();
        applyStates();

        previousSystemState = systemState;
    }

    public SystemState handleStateTransition() {
        return switch (wantedState) {
            case IDLE -> SystemState.IDLING;
            case MOVE_BY_SPEED -> SystemState.MOVING_BY_SPEED;
            case DISABLE -> SystemState.DISABLED;
        };
    }

    public void applyStates() {
        switch (systemState) {
            case IDLING -> idleState();
            case MOVING_BY_SPEED -> moveBySpeed();
            case DISABLED -> disabled();
        }
    }

    private void idleState() {
        shooterIO.setDutyCycle(0);
    }

    private void moveBySpeed() {
        shooterIO.setDutyCycle(targetSpeed);
    }

    private void disabled() {
        if (previousSystemState != SystemState.DISABLED) {
            shooterIO.setDutyCycle(0);
            shooterIO.setNeutralMode(NeutralModeValue.Coast);
        }
    }
}