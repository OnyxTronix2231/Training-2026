package frc.robot.subsystems.lecture.elevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.OnyxMotorOutputs.CanBus;

import static frc.robot.subsystems.lecture.elevator.ElevatorConstants.ELEVATOR_MOTOR_NAME;
import static frc.robot.subsystems.lecture.elevator.ElevatorConstants.ELEVATOR_SUBSYSTEM_NAME;
import static frc.robot.subsystems.lecture.elevator.ElevatorConstants.ElevatorRobotAConstants.*;

public class ElevatorIORobotA implements ElevatorIO {
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final OnyxMotorOutputs elevatorMasterMotorOutputs;
    private final OnyxMotorOutputs elevatorFollowerMotorOutputs;

    public ElevatorIORobotA() {
        masterMotor = new TalonFX(ROBOT_A_ELEVATOR_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(ROBOT_A_ELEVATOR_FOLLOWER_MOTOR_ID);

        elevatorMasterMotorOutputs = new OnyxMotorOutputs(masterMotor, ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_MOTOR_NAME, ROBOT_A_ELEVATOR_ROTATIONS_TO_METERS, CanBus.RIO);
        elevatorFollowerMotorOutputs = new OnyxMotorOutputs(masterMotor, ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_MOTOR_NAME, ROBOT_A_ELEVATOR_ROTATIONS_TO_METERS, CanBus.RIO);

        masterMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());

        followerMotor.setControl(new Follower(ROBOT_A_ELEVATOR_MASTER_MOTOR_ID, MotorAlignmentValue.Opposed));
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }

    @Override
    public void updateInputs(ElevatorInputs inputs) {
        elevatorMasterMotorOutputs.updateOutputs();
        inputs.elevatorMasterMotorOutputs = elevatorMasterMotorOutputs;

        elevatorFollowerMotorOutputs.updateOutputs();
        inputs.elevatorFollowerMotorOutputs = elevatorFollowerMotorOutputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }

    public double getCurrentHeight() {
        return elevatorMasterMotorOutputs.getMotorValue().getAsDouble();
    }
}
