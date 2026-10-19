package frc.robot.subsystems.lecture.wierdElevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.OnyxMotorOutputs.*;

import static frc.robot.subsystems.lecture.wierdElevator.WierdElevatorConstants.*;
import static frc.robot.subsystems.lecture.wierdElevator.WierdElevatorConstants.WierdElevatorRobotBConstants.*;

public class WierdElevatorIORobotB implements WierdElevatorIO {

    private final TalonFX motor;
    private final OnyxMotorOutputs motorOutputs;

    public WierdElevatorIORobotB() {
        motor = new TalonFX(WIERD_ELEVATOR_MOTOR_ID);
        motorOutputs = new OnyxMotorOutputs(motor, WIERD_ELEVATOR_SUBSYSTEM_NAME, WIERD_ELEVATOR_MOTOR_NAME, CanBus.RIO);
        motor.getConfigurator().apply(getMotorConfiguration());
    }

    public TalonFXConfiguration getMotorConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        return configuration;
    }

    @Override
    public void updateOutputs(WierdElevatorOutputs elevatorOutputs) {
        motorOutputs.updateOutputs();
        elevatorOutputs.masterMotorOutputs = motorOutputs;
        elevatorOutputs.followerMotorOutputs = motorOutputs;
    }

    @Override
    public void moveToHeight(double height) {
        return;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public double getHeight() {
        return  WIERD_ELEVATOR_HEIGHT_PER_ROTATION * (motorOutputs.getMotorRawValue() / WIERD_ELEVATOR_MOTOR_TICKS_IN_ROTATION);
    }

    @Override
    public void moveToStartingPosition() {
        return;
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {
        motor.setNeutralMode(neutralMode);
    }
}

