package frc.robot.subsystems.answers.L8;

import edu.wpi.first.math.filter.Debouncer;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.PID.PIDValues;
import frc.robot.lib.advancedSubsystem.PositionBasedSubsystem;
import org.littletonrobotics.junction.Logger;

import java.util.List;

import static frc.robot.subsystems.answers.L8.ElevatorConstants.*;

public class Elevator extends PositionBasedSubsystem {
    private final ElevatorIO.ElevatorInputs elevatorInputs;
    private final ElevatorIO elevatorIO;

    public enum WantedState {
        IDLE,
        MOVE_TO_POSITION,
        MOVE_BY_SPEED,
    }

    public enum SystemState {
        IDLING,
        MOVING_TO_POSITION,
        MOVING_BY_SPEED,
    }

    private WantedState wantedState;
    private SystemState systemState;

    private double targetPosition;
    private double targetSpeed;

    public WantedState getWantedState() {
        return wantedState;
    }

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public void setWantedState(WantedState wantedState, double targetPosition) {
        this.wantedState = wantedState;
        this.targetPosition = targetPosition;
    }

    public void setWantedStateWithSpeed(WantedState wantedState, double targetSpeed) {
        this.wantedState = wantedState;
        this.targetSpeed = targetSpeed;
    }

    public SystemState getSystemState() {
        return systemState;
    }

    public Elevator(ElevatorIO elevatorIO) {
        super(ELEVATOR_DEFAULT_POSITION);
        this.elevatorInputs = new ElevatorIO.ElevatorInputs();
        this.elevatorIO = elevatorIO;
        this.elevatorIO.updateInputs(elevatorInputs);

        this.wantedState = WantedState.IDLE;
        this.systemState = SystemState.IDLING;

        this.targetPosition = 0;
        this.targetSpeed = 0;
    }

    @Override
    public void periodic() {
        elevatorIO.updateInputs(elevatorInputs);

        systemState = handleStateTransition();

        applyStates();

        log();
    }

    private SystemState handleStateTransition() {
        return switch(wantedState) {
            case IDLE -> SystemState.IDLING;
            case MOVE_TO_POSITION -> SystemState.MOVING_TO_POSITION;
            case MOVE_BY_SPEED -> SystemState.MOVING_BY_SPEED;
        };
    }

    private void applyStates() {
        switch(systemState) {
            case IDLING -> idleState();
            case MOVING_TO_POSITION -> moveToPosition();
            case MOVING_BY_SPEED -> moveBySpeed();
        }
    }

    private void log() {
        elevatorInputs.elevatorMasterMotorInputs.log();
        elevatorInputs.elevatorFollowerMotorInputs.log();

        Logger.recordOutput("Subsystems/Elevator/limitSwitchPressed", elevatorInputs.isSwitchPressed);
        Logger.recordOutput("Subsystems/Elevator/encoderPosition", elevatorInputs.isSwitchPressed);

        Logger.recordOutput("Subsystems/Elevator/wantedState", wantedState);
        Logger.recordOutput("Subsystems/Elevator/systemState", systemState);
    }

    @Override
    protected void idleState() {
        elevatorIO.setDutyCycle(0);
    }

    @Override
    protected void moveBySpeed() {
        elevatorIO.setDutyCycle(targetSpeed);
    }

    @Override
    protected void setWantedSpeed(double dutyCycle) {
        this.targetSpeed = dutyCycle;
    }

    Debouncer isOnTargetDebouncer = new Debouncer(ELEVATOR_ON_TARGET_DEBOUNCE_TIME);

    @Override
    public boolean isOnTarget() {
        return isOnTarget(targetPosition);
    }

    @Override
    public boolean isOnTarget(double value) {
        return isOnTargetDebouncer.calculate(Math.abs(getElevatorPosition() - value) <= ELEVATOR_ON_TARGET_TOLERANCE );
    }

    @Override
    protected void setWantedPosition(double position) {
        this.targetPosition = position;
    }

    @Override
    protected double getForwardsSoftLimit() {
        return elevatorIO.getElevatorForwardSoftLimit();
    }

    @Override
    protected double getBackwardsSoftLimit() {
        return elevatorIO.getElevatorBackwardSoftLimit();
    }

    @Override
    protected void moveToPosition() {
        elevatorIO.moveToPosition(targetPosition);
    }

    @Override
    protected List<OnyxMotorOutputs> getMotorInputs() {
        return List.of(elevatorInputs.elevatorMasterMotorInputs, elevatorInputs.elevatorFollowerMotorInputs);
    }

    @Override
    protected double getTotalCurrent() {
        return elevatorInputs.elevatorMasterMotorInputs.getMotorSupplyCurrentAmps() + elevatorInputs.elevatorFollowerMotorInputs.getMotorSupplyCurrentAmps();
    }

    @Override
    protected void setWantedState(GlobalWantedState wantedState) {
        setWantedState(WantedState.valueOf(wantedState.toString()));
    }

    public void updatePID(PIDValues pidValues) {
        elevatorIO.updatePID(pidValues);
    }

    public double getElevatorPosition() {
        return elevatorInputs.elevatorMasterMotorInputs.getMotorValue().getAsDouble();
    }

    public double getElevatorVelocity() {
        return elevatorInputs.elevatorMasterMotorInputs.getMotorAngularVelocityRotPerSec();
    }

    public double getElevatorAcceleration() {
        return elevatorInputs.elevatorMasterMotorInputs.getMotorAngularAccelerationRotPerSecSquared();
    }

    private static Elevator instance;

    public static void init(ElevatorIO elevatorIO) {
        if(instance == null) {
            instance = new Elevator(elevatorIO);
        }
    }

    public static Elevator getInstance() {
        return instance;
    }
}
