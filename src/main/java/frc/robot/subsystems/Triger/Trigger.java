package frc.robot.subsystems.Triger;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.advancedSubsystem.VelocityBasedSubsystem;
import frc.robot.subsystems.Triger.TriggerIOSimulation;

import java.util.List;

public class Trigger extends VelocityBasedSubsystem {
    private final TriggerIO.TrigerOutputs triggerOutputs;
    private final TriggerIO triggerIO;
    private static Trigger instance;


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

    private Trigger.WantedState wantedState;
    private SystemState systemState;
    private Trigger.SystemState previousSystemState;

    public void setWantedState(Trigger.WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public Trigger.WantedState getWantedState() {
        return wantedState;
    }

    public Trigger.SystemState getSystemState() {
        return systemState;
    }

    public Trigger(TriggerIO trigerIO) {
        this.triggerOutputs = new TriggerIO.TrigerOutputs();
        this.triggerIO = trigerIO;
        this.triggerIO.updateOutputs(triggerOutputs);

        this.wantedState = Trigger.WantedState.IDLE;
        this.systemState = Trigger.SystemState.IDLING;
        this.previousSystemState = Trigger.SystemState.IDLING;
    }

    @Override
    public void periodic() {
        triggerIO.updateOutputs(triggerOutputs);

        systemState = handleStateTransition();
        applyStates();

        previousSystemState = systemState;
    }

    public Trigger.SystemState handleStateTransition() {
        return switch (wantedState) {
            case IDLE -> Trigger.SystemState.IDLING;
            case MOVE_BY_SPEED -> Trigger.SystemState.MOVING_BY_SPEED;
            case DISABLE -> Trigger.SystemState.DISABLED;
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

    private double targetSpeed;

    public void setTargetSpeed(double targetSpeed) {
        this.targetSpeed = targetSpeed;
    }

    public void moveBySpeed() {
        triggerIO.setDutyCycle(targetSpeed);
    }

    @Override
    protected void setWantedSpeed(double dutyCycle) {

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
        return List.of();
    }

    @Override
    protected double getTotalCurrent() {
        return 0;
    }

    @Override
    protected void setWantedState(GlobalWantedState wantedState) {

    }

    private void disabled() {
        if (previousSystemState != Trigger.SystemState.DISABLED) {
            triggerIO.setDutyCycle(0);
            triggerIO.setNeutralMode(NeutralModeValue.Coast);
        }
    }

    public static void Init(TriggerIO triggerIO) {
        if (instance == null) {
            instance = new Trigger(triggerIO);
        }
    }

    public static Trigger getInstance() {
        return instance;    }
    public double getRPS() {
        return triggerOutputs.;
    }

}






