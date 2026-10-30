package frc.robot.subsystems.Hinge;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.advancedSubsystem.VelocityBasedSubsystem;
import frc.robot.subsystems.Trigger.Trigger;

import java.util.List;

public class Hinge extends VelocityBasedSubsystem {
    private static Hinge instance;
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

    public Hinge(HingeIO hingeIO) {
        this.hingeOutputs = new HingeIO.HingeOutputs();
        this.hingeIO = hingeIO;
        this.hingeIO.updateOutputs(hingeOutputs);

        this.wantedState = WantedState.IDLE;
        this.systemState = SystemState.IDLING;
        this.previousSystemState = SystemState.IDLING;
    }
    public static void init(HingeIO hingeIO){
        if(instance == null){
            instance = new Hinge(hingeIO);
        }
    }
    @Override
    public void periodic() {
        hingeIO.updateOutputs(hingeOutputs);

        systemState = handleStateTransition();
        applyStates();

        previousSystemState = systemState;
    }

    private void applyStates() {
        switch (systemState) {
            case IDLING -> idleState();
            case MOVING_BY_SPEED -> moveBySpeed();
            case DISABLED -> disabled();
            case TESTING -> runTest();
        }
    }

    private SystemState handleStateTransition() {
        return switch (wantedState) {
            case IDLE -> SystemState.IDLING;
            case MOVE_BY_SPEED -> SystemState.MOVING_BY_SPEED;
            case DISABLE -> SystemState.DISABLED;
            case TEST -> SystemState.TESTING;
        };
    }

    @Override
    protected void idleState() {
        hingeIO.setDutyCycle(0);
    }

    private double targetSpeed;

    public void setTargetSpeed(double targetSpeed) {
        this.targetSpeed = targetSpeed;
    }

    @Override
    protected void moveBySpeed() {
        hingeIO.setDutyCycle(targetSpeed);
    }

    private void disabled() {
        if (previousSystemState != SystemState.DISABLED) {
            hingeIO.setDutyCycle(0);
            hingeIO.setNeutralMode(NeutralModeValue.Coast);
        }
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
        return List.of(hingeOutputs.hingeMotorOutputs);
    }

    @Override
    protected double getTotalCurrent() {
        return hingeOutputs.hingeMotorOutputs.getMotorSupplyCurrentAmps();
    }

    @Override
    protected void setWantedState(GlobalWantedState wantedState) {
        setWantedState(Hinge.WantedState.valueOf(wantedState.toString()));

    }
    public double getAngle(){
        return hingeIO.getAngle();
    }

    public static Hinge getInstance(){
        return instance;
    }
}
