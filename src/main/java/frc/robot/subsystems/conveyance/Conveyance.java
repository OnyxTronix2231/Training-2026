package frc.robot.subsystems.conveyance;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.advancedSubsystem.VelocityBasedSubsystem;
import frc.robot.subsystems.Hinge.Hinge;
import frc.robot.subsystems.trigger.Trigger;
import frc.robot.subsystems.trigger.TriggerIO;

import java.util.List;

public class Conveyance extends VelocityBasedSubsystem {

    private final ConveyanceIO.ConveyanceOutputs conveyanceOutputs;
    private final ConveyanceIO conveyanceIO;

    private double targetSpeed;

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

    public Conveyance(ConveyanceIO conveyanceIO) {
        this.conveyanceOutputs = new ConveyanceIO.ConveyanceOutputs();
        this.conveyanceIO = conveyanceIO;
        this.conveyanceIO.updateOutputs(conveyanceOutputs);

        this.wantedState = WantedState.IDLE;
        this.systemState = SystemState.IDLING;
        this.previousSystemState = SystemState.IDLING;
    }

    @Override
    public void periodic() {
        conveyanceIO.updateOutputs(conveyanceOutputs);

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

    public void idleState() {
        conveyanceIO.setDutyCycle(0);
    }

    public void moveBySpeed() {
        conveyanceIO.setDutyCycle(targetSpeed);
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
    protected List<OnyxMotorOutputs> getMotorInputs() {
        return List.of(conveyanceOutputs.masterMotorOutputs, conveyanceOutputs.followerMotorOutputs);
    }

    @Override
    protected double getTotalCurrent() {
        return conveyanceOutputs.masterMotorOutputs.getMotorSupplyCurrentAmps() + conveyanceOutputs.followerMotorOutputs.getMotorSupplyCurrentAmps();
    }

    @Override
    protected void setWantedState(GlobalWantedState wantedState) {
        setWantedState(Conveyance.WantedState.valueOf(wantedState.toString()));
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

    public double getRPS(){
        return conveyanceOutputs.masterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }

    private void disabled() {
        if (previousSystemState != SystemState.DISABLED) {
            conveyanceIO.setDutyCycle(0);
            conveyanceIO.setNeutralMode(NeutralModeValue.Coast);
        }
    }

    private static Conveyance instance;

    public static void init(ConveyanceIO conveyanceIO) {
        if (instance == null) {
            instance = new Conveyance(conveyanceIO);
        }
    }

    public static Conveyance getInstance() {
        return instance;
    }
}