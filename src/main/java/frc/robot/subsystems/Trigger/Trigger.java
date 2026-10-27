package frc.robot.subsystems.Trigger;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.advancedSubsystem.VelocityBasedSubsystem;

import java.util.List;

public class Trigger extends VelocityBasedSubsystem {

    private final TriggerIO.HingeOutputs triggerOutputs;
    private final TriggerIO triggerIO;


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

    private double targetSpeed;

    public Trigger(TriggerIO triggerIO) {
        this.triggerOutputs = new TriggerIO.HingeOutputs();
        this.triggerIO = triggerIO;
        this.triggerIO.updateOutputs(triggerOutputs);

        this.wantedState = WantedState.IDLE;
        this.systemState = SystemState.IDLING;
        this.previousSystemState = SystemState.IDLING;

        targetSpeed = 0;
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
        };
    }

    public void applyStates() {
        switch (systemState) {
            case IDLING -> idleState();
            case MOVING_BY_SPEED -> moveBySpeed();
            case DISABLED -> disabled();
        }
    }

    public void idleState() {
        triggerIO.setDutyCycle(0);
    }

    public void moveBySpeed() {
        triggerIO.setDutyCycle(targetSpeed);
    }

    @Override
    public void setWantedSpeed(double dutyCycle) {
        targetSpeed = dutyCycle;
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
        return List.of(triggerOutputs.TriggerMotorOutput);
    }

    @Override
    protected double getTotalCurrent() {
        return 0;
    }

    @Override
    protected void setWantedState(GlobalWantedState wantedState) {
        setWantedState(WantedState.valueOf(wantedState.toString()));

    }

    private void disabled() {
        if (previousSystemState != Trigger.SystemState.DISABLED) {
            triggerIO.setDutyCycle(0);
            triggerIO.setNeutralMode(NeutralModeValue.Coast);
        }
    }

    public double getRPS() {
        return triggerOutputs.TriggerMotorOutput.getMotorAngularVelocityRotPerSec();
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

    private static Trigger instance;

    public static void Init(TriggerIO triggerIO) {
        if (instance == null) {
            instance = new Trigger(triggerIO);
        }
    }

    public static Trigger getInstance() {
        return instance;
    }
}