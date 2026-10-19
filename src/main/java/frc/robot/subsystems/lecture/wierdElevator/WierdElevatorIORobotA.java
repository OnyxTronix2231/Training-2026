package frc.robot.subsystems.lecture.wierdElevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.subsystems.lecture.wierdElevator.WierdElevatorConstants.*;
import static frc.robot.subsystems.lecture.wierdElevator.WierdElevatorConstants.WierdElevatorRobotAConstants.*;

public class WierdElevatorIORobotA implements WierdElevatorIO {

    private final TalonFX masterMotor;
    private final TalonFX followerMotor;
    private final OnyxMotorOutputs masterMotorOutputs;
    private final OnyxMotorOutputs followerMotorOutputs;

    public WierdElevatorIORobotA() {
        masterMotor = new TalonFX(WIERD_ELEVATOR_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(WIERD_ELEVATOR_FOLLOWER_MOTOR_ID);
        masterMotorOutputs = new OnyxMotorOutputs(masterMotor, WIERD_ELEVATOR_SUBSYSTEM_NAME, WIERD_ELEVATOR_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        followerMotorOutputs = new OnyxMotorOutputs(followerMotor, WIERD_ELEVATOR_SUBSYSTEM_NAME, WIERD_ELEVATOR_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);

        masterMotor.getConfigurator().apply(getMotorConfiguration());
        followerMotor.getConfigurator().apply(getMotorConfiguration());

        followerMotor.setControl(new Follower(WIERD_ELEVATOR_MASTER_MOTOR_ID, MotorAlignmentValue.Opposed));
    }

    public TalonFXConfiguration getMotorConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        return configuration;
    }

    @Override
    public void updateOutputs(WierdElevatorOutputs elevatorOutputs) {
        masterMotorOutputs.updateOutputs();
        followerMotorOutputs.updateOutputs();
        elevatorOutputs.masterMotorOutputs = masterMotorOutputs;
        elevatorOutputs.followerMotorOutputs = followerMotorOutputs;
    }

    @Override
    public void moveToHeight(double height) {
        return;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }

    @Override
    public double getHeight() {
        return WIERD_ELEVATOR_HEIGHT_PER_ROTATION * (masterMotorOutputs.getMotorRawValue() / WIERD_ELEVATOR_MOTOR_TICKS_IN_ROTATION);
    }

    @Override
    public void moveToStartingPosition() {
        return;
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {
        masterMotor.setNeutralMode(neutralMode);
    }
}
