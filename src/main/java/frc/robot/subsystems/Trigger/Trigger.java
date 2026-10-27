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

    private Trigger.WantedState wantedState;
    private Trigger.SystemState systemState;
    private Trigger.SystemState previousSystemState;


    public Trigger(TriggerIO triggerIO) {
        this.triggerOutputs = new TriggerIO.TriggerOutputs();
        this.triggerIO = triggerIO;
        this.triggerIO.updateOutputs(triggerOutputs);

        this.wantedState = Trigger.WantedState.IDLE;
        this.systemState = Trigger.SystemState.IDLING;
        this.previousSystemState = Trigger.SystemState.IDLING;
    }

    public void periodic() {
        triggerIO.updateOutputs(triggerOutputs);

        systemState = handleStateTransition();

        applyStates();

        previousSystemState = systemState;
    }

    public Trigger.SystemState handleStateTransition() {
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

    @Override
    public void idleState() {
        triggerIO.setDutyCycle(0);
    }

    private double targetSpeed;

    @Override
    public void moveBySpeed() {
        triggerIO.setDutyCycle(targetSpeed);
    }

    private void disabled() {
        if (previousSystemState != Trigger.SystemState.DISABLED) {
            triggerIO.setDutyCycle(0);
            triggerIO.setNeutralMode(NeutralModeValue.Coast);
        }
    }
    public double getRPS() {
        return triggerOutputs.motorOutputs.getMotorAngularVelocityRotPerSec();
    }

    @Override
    protected double getTotalCurrent() {
        return triggerOutputs.motorOutputs.getMotorStatorCurrentAmps();
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
    protected void setWantedState(GlobalWantedState wantedState) {
        setWantedState(WantedState.valueOf(wantedState.toString()));
    }

    @Override
    protected List<OnyxMotorOutputs> getMotorInputs() {
        return List.of(triggerOutputs.motorOutputs);
    }

    public void setWantedState(Trigger.WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public Trigger.WantedState getWantedState() {
        return wantedState;
    }

    public Trigger.SystemState getSystemState() {
        return systemState;
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
