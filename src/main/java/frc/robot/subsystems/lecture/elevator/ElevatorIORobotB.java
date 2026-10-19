package frc.robot.subsystems.lecture.elevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.OnyxMotorOutputs.CanBus;

import static frc.robot.subsystems.lecture.elevator.ElevatorConstants.ElevatorRobotBConstants.*;
import static frc.robot.subsystems.lecture.elevator.ElevatorConstants.*;

public class ElevatorIORobotB implements ElevatorIO {
    private final TalonFX motor;

    private final OnyxMotorOutputs elevatorMotorOutputs;

    public ElevatorIORobotB() {
        motor = new TalonFX(ROBOT_B_ELEVATOR_MOTOR_ID);

        elevatorMotorOutputs = new OnyxMotorOutputs(motor, ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_MOTOR_NAME, ROBOT_B_ELEVATOR_ROTATIONS_TO_METERS, CanBus.RIO);

        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }

    @Override
    public void updateInputs(ElevatorInputs inputs) {
        elevatorMotorOutputs.updateOutputs();
        inputs.elevatorMasterMotorOutputs = elevatorMotorOutputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    public double getCurrentHeight() {
        return elevatorMotorOutputs.getMotorValue().getAsDouble();
    }
}
