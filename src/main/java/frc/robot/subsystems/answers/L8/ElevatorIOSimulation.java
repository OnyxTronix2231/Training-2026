package frc.robot.subsystems.answers.L8;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorInputs;
import frc.robot.lib.PID.PIDValues;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.answers.L8.ElevatorConstants.*;
import static frc.robot.subsystems.answers.L8.ElevatorConstants.ElevatorSimulationConstants.*;

public class ElevatorIOSimulation implements ElevatorIO {
    private final TalonFX motor;

    private final DCMotorSim simulatedMotor;

    private final OnyxMotorInputs elevatorMasterMotorInputs;
    private final OnyxMotorInputs elevatorFollowerMotorInputs;

    private final PositionVoltage positionVoltage;

    public ElevatorIOSimulation() {
        motor = new TalonFX(SIMULATION_ELEVATOR_MOTOR_ID);

        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_ELEVATOR_NUM_OF_MOTORS),
            SingleJointedArmSim.estimateMOI(SIMULATION_ELEVATOR_LENGTH_METERS, SIMULATION_ELEVATOR_MASS_KG), 1),
            DCMotor.getKrakenX60(SIMULATION_ELEVATOR_NUM_OF_MOTORS));

        elevatorMasterMotorInputs = new OnyxMotorInputs(motor, ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_MASTER_MOTOR_NAME, SIMULATION_ROTATIONS_TO_LENGTH);
        elevatorFollowerMotorInputs = new OnyxMotorInputs();

        elevatorMasterMotorInputs.updateInputs();

        motor.getConfigurator().apply(getTalonFXConfiguration());

        positionVoltage = new PositionVoltage(0);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.Slot0 = SIMULATION_ELEVATOR_PID_VALUES.pidValuesToSlot0Configs();

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = SIMULATION_LENGTH_TO_ROTATIONS(ELEVATOR_FORWARD_SOFT_LIMIT_METERS);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = SIMULATION_LENGTH_TO_ROTATIONS(ELEVATOR_REVERSE_SOFT_LIMIT_METERS);

        return configuration;
    }

    @Override
    public void updateInputs(ElevatorInputs inputs) {
        updateMotor(motor, simulatedMotor);

        elevatorMasterMotorInputs.updateInputs();

        inputs.elevatorMasterMotorInputs = elevatorMasterMotorInputs;
        inputs.elevatorFollowerMotorInputs = elevatorFollowerMotorInputs;

        inputs.isSwitchPressed = false;
        inputs.encoderPosition = 0;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void moveToPosition(double position) {
        motor.setControl(positionVoltage.withPosition(position));
    }

    @Override
    public void updatePID(PIDValues pidValues) {
        pidValues.updatePIDValues(motor);
    }

    @Override
    public double getElevatorForwardSoftLimit() {
        return ELEVATOR_FORWARD_SOFT_LIMIT_METERS;
    }

    @Override
    public double getElevatorBackwardSoftLimit() {
        return ELEVATOR_REVERSE_SOFT_LIMIT_METERS;
    }
}
