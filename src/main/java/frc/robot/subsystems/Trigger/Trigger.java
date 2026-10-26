package frc.robot.subsystems.Trigger;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.advancedSubsystem.VelocityBasedSubsystem;

import java.util.List;

public class Trigger extends VelocityBasedSubsystem {

    private final TriggerIO.TriggerOutputs triggerOutputs;
    private final TriggerIO triggerIO;

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

    private WantedState wantedState;
    private SystemState systemState;
    private SystemState previousSystemState;

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public WantedState getWantedState() {
        return wantedState;
    }

    public SystemState getSystemState() {
        return systemState;
    }

    public Trigger(TriggerIO triggerIO) {
        this.triggerOutputs = new TriggerIO.TriggerOutputs();
        this.triggerIO = triggerIO;
        this.triggerIO.updateOutputs(triggerOutputs);

        this.wantedState = WantedState.IDLE;
        this.systemState = SystemState.IDLING;
        this.previousSystemState = SystemState.IDLING;
    }

    @Override
    public void periodic() {
        triggerIO.updateOutputs(triggerOutputs);

        systemState = handleStateTransition();
        applyStates();

        previousSystemState = systemState;
    }

    public SystemState handleStateTransition() {
        return switch (wantedState) {
            case IDLE -> SystemState.IDLING;
            case MOVE_BY_SPEED -> SystemState.MOVING_BY_SPEED;
            case DISABLE -> SystemState.DISABLED;
            case TEST -> SystemState.TESTING;
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
    private void disabled() {
        if (previousSystemState != SystemState.DISABLED) {
            triggerIO.setDutyCycle(0);
            triggerIO.setNeutralMode(NeutralModeValue.Coast);
        }
    }
@Override
    public void idleState() {
        triggerIO.setDutyCycle(0);
    }

    private double targetSpeed;

    public void setTargetSpeed(double targetSpeed) {
        this.targetSpeed = targetSpeed;
    }
@Override
    public void moveBySpeed() {
        triggerIO.setDutyCycle(targetSpeed);
    }

    @Override
    protected void setWantedSpeed(double dutyCycle) {
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
    protected void setWantedRPS(double RPS) {

    }

    @Override
    protected double getMaxRPS() {
        return 0;
    }

    @Override
    protected void moveByRPS() {

    }

    @Override
    protected List<OnyxMotorOutputs> getMotorInputs() {
        return List.of(triggerOutputs.triggerMasterMotorOutputs, triggerOutputs.triggerFollowerMotorOutputs);
    }

    @Override
    protected double getTotalCurrent() {
        return triggerOutputs.triggerMasterMotorOutputs.getMotorSupplyCurrentAmps() +
                triggerOutputs.triggerFollowerMotorOutputs.getMotorSupplyCurrentAmps();
    }

    @Override
    protected void setWantedState(GlobalWantedState wantedState) {
        setWantedState(WantedState.valueOf(wantedState.toString()));
    }
    private static Trigger instance;

    public static void init(TriggerIO triggerIO) {
        if (instance == null) {
            instance = new Trigger(triggerIO);
        }
    }
    public static Trigger getInstance() {
        return instance;
    }
}
