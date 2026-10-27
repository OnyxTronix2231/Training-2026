package frc.robot.subsystems.Trigger;

import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.advancedSubsystem.VelocityBasedSubsystem;

import java.util.List;

import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_MAX_RPS;

public class Trigger extends VelocityBasedSubsystem {

    private final TriggerIO.TriggerOutputs triggerOutputs;
    private final TriggerIO triggerIO;

    public enum WantedState {
        IDLE,
        MOVE_BY_SPEED,
        TEST,
        DISABLED
    }

    public enum SystemState {
        IDLING,
        MOVING_BY_SPEED,
        TESTING,
        DISABLED
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
            case TEST -> SystemState.TESTING;
            case DISABLED -> SystemState.DISABLED;
        };
    }

    public void applyStates() {
        switch (systemState) {
            case IDLING -> idleState();
            case MOVING_BY_SPEED -> moveBySpeed();
            case TESTING -> runTest();
            case DISABLED -> disabled();
        }
    }

    public void idleState() {
        triggerIO.setDutyCycle(0);
    }

    private double targetSpeed;

    public void setTargetSpeed(double targetSpeed) {
        this.targetSpeed = targetSpeed;
    }

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

    public double getRPS() {
        return triggerOutputs.triggerMotorOutputs.getMotorAngularVelocityRotPerSec();
    }

    @Override
    protected double getMaxRPS() {
        return TRIGGER_MAX_RPS;

    }

    @Override
    protected void moveByRPS() {

    }

    @Override
    protected List<OnyxMotorOutputs> getMotorInputs() {
        return List.of(triggerOutputs.triggerMotorOutputs);
    }

    @Override
    protected double getTotalCurrent() {
        return triggerOutputs.triggerMotorOutputs.getMotorStatorCurrentAmps();
    }

    @Override
    protected void setWantedState(GlobalWantedState wantedState) {
        setWantedState(WantedState.valueOf(wantedState.toString()));
    }

    private void disabled() {
        if (previousSystemState != SystemState.DISABLED) {
            triggerIO.setDutyCycle(0);
        }
    }

    private static Trigger instance;

    public static void init(TriggerIO triggerIO){
        if (instance == null){
            instance = new Trigger(triggerIO);
        }
    }
    public static Trigger getInstance(){
        return instance;
    }
}
