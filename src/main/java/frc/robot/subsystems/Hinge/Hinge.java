package frc.robot.subsystems.Hinge;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.advancedSubsystem.PositionBasedSubsystem;

import java.util.List;

public class Hinge extends PositionBasedSubsystem {

    private final HingeIO.HingeOutputs hingeOutputs;
    private final HingeIO hingeIO;

    public enum WantedState {
        IDLE,
        MOVE_BY_SPEED,
        DISABLE,
        TEST
    }

    public enum SystemState {
        IDLING,
        MOVING_BY_SPEED,
        DISABLED,
        TESTING
    }

    private Hinge.WantedState wantedState;
    private Hinge.SystemState systemState;
    private Hinge.SystemState previousSystemState;

    public Hinge(HingeIO hingeIO) {
        super(0,0);
        this.hingeOutputs = new HingeIO.HingeOutputs();
        this.hingeIO = hingeIO;
        this.hingeIO.updateOutputs(hingeOutputs);

        this.wantedState = Hinge.WantedState.IDLE;
        this.systemState = Hinge.SystemState.IDLING;
        this.previousSystemState = Hinge.SystemState.IDLING;
    }

    public void periodic() {
        hingeIO.updateOutputs(hingeOutputs);

        systemState = handleStateTransition();

        applyStates();

        previousSystemState = systemState;
    }

    public Hinge.SystemState handleStateTransition() {
        return switch (wantedState) {
            case IDLE -> Hinge.SystemState.IDLING;
            case MOVE_BY_SPEED -> Hinge.SystemState.MOVING_BY_SPEED;
            case DISABLE -> Hinge.SystemState.DISABLED;
            case TEST -> Hinge.SystemState.TESTING;
        };
    }

    public void applyStates() {
        switch (systemState) {
            case IDLING -> idleState();
            case MOVING_BY_SPEED -> moveBySpeed();
            case DISABLED -> disabled();
            case TESTING -> runTest();
        }
    }

    @Override
    public void idleState() {
        hingeIO.setDutyCycle(0);
    }

    private double targetSpeed;

    @Override
    public void moveBySpeed() {
        hingeIO.setDutyCycle(targetSpeed);
    }

    private void disabled() {
        if (previousSystemState != Hinge.SystemState.DISABLED) {
            hingeIO.setDutyCycle(0);
            hingeIO.setNeutralMode(NeutralModeValue.Coast);
        }
    }
    public double getAngle() {
        return hingeOutputs.motorOutputs.getMotorValue().getAsDouble();
    }

    @Override
    protected double getTotalCurrent() {
        return hingeOutputs.motorOutputs.getMotorStatorCurrentAmps();
    }

    @Override
    protected void setWantedState(GlobalWantedState wantedState) {
        setWantedState(Hinge.WantedState.valueOf(wantedState.toString()));
    }

    @Override
    public void setWantedSpeed(double dutyCycle) {
        this.targetSpeed = dutyCycle;
    }

    @Override
    public boolean isOnTarget() {
        return false;
    }

    @Override
    public boolean isOnTarget(double value) {
        return false;
    }

    @Override
    protected void setWantedPosition(double position) {

    }

    @Override
    protected double getForwardsSoftLimit() {
        return 0;
    }

    @Override
    protected double getBackwardsSoftLimit() {
        return 0;
    }

    @Override
    protected void moveToPosition() {

    }

    @Override
    protected List<OnyxMotorOutputs> getMotorInputs() {
        return List.of(hingeOutputs.motorOutputs);
    }

    public void setWantedState(Hinge.WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public Hinge.WantedState getWantedState() {
        return wantedState;
    }

    public Hinge.SystemState getSystemState() {
        return systemState;
    }

    private static Hinge instance;

    public static void init(HingeIO hingeIO) {
        if (instance == null) {
            instance = new Hinge(hingeIO);
        }

    }

    public static Hinge getInstance() {
        return instance;
    }
}
