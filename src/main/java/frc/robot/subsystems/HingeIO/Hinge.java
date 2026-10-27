package frc.robot.subsystems.HingeIO;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.advancedSubsystem.AdvancedSubsystem;
import frc.robot.lib.advancedSubsystem.PositionBasedSubsystem;


import java.util.List;

public class Hinge extends PositionBasedSubsystem {
    private final frc.robot.subsystems.HingeIO.HingeIO.HingeOutputs hingeOutputs;
    private final HingeIO hingeIO;


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

    private Hinge.WantedState wantedState;
    private Hinge.SystemState systemState;
    private Hinge.SystemState previousSystemState;

    private double targetSpeed;

    public Hinge(HingeIO hingeIO) {
        super(0,0);
        this.hingeOutputs = new HingeIO.HingeOutputs();
        this.hingeIO = hingeIO;
        this.hingeIO.updateOutputs(hingeOutputs);

        this.wantedState = Hinge.WantedState.IDLE;
        this.systemState = Hinge.SystemState.IDLING;
        this.previousSystemState = Hinge.SystemState.IDLING;

        targetSpeed = 0;
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
        hingeIO.setDutyCycle(0);
    }

    public void moveBySpeed() {
        hingeIO.setDutyCycle(targetSpeed);
    }


    public void setWantedSpeed(double dutyCycle) {
        targetSpeed = dutyCycle;
    }


    public boolean isOnTarget() {
        return false;
    }


    public boolean isOnTarget(double value) {
        return false;
    }


    protected void setWantedRPS(double RPS) {
        return;

    }


    protected double getMaxRPS() {
        return 0;
    }


    protected void moveByRPS() {

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


    protected List<OnyxMotorOutputs> getMotorInputs() {
        return List.of(hingeOutputs.HingeMotorOutput);
    }


    protected double getTotalCurrent() {
        return 0;
    }


    protected void setWantedState(AdvancedSubsystem.GlobalWantedState wantedState) {
        setWantedState(Hinge.WantedState.valueOf(wantedState.toString()));

    }

    private void disabled() {
        if (previousSystemState != SystemState.DISABLED) {
            hingeIO.setDutyCycle(0);
            hingeIO.setNeutralMode(NeutralModeValue.Coast);
        }
    }

    public double getRPS() {
        return hingeOutputs.HingeMotorOutput.getMotorAngularVelocityRotPerSec();
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

    public static void Init(HingeIO hingeIO) {
        if (instance == null) {
            instance = new Hinge(hingeIO);
        }
    }

    public static Hinge getInstance() {
        return instance;
    }
    public  double getAngle(){
        return hingeOutputs.HingeMotorOutput.getValue();
    }
}

