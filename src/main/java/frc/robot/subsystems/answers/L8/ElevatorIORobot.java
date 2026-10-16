package frc.robot.subsystems.answers.L8;

import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.*;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.PID.PIDValues;

import static frc.robot.subsystems.answers.L8.ElevatorConstants.*;
import static frc.robot.subsystems.answers.L8.ElevatorConstants.ElevatorRobotConstants.*;

public class ElevatorIORobot implements ElevatorIO {
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final OnyxMotorOutputs elevatorMasterMotorInputs;
    private final OnyxMotorOutputs elevatorFollowerMotorInputs;

    private final DigitalInput limitSwitch;
    private final CANcoder encoder;

    private final PositionVoltage positionVoltage;

    public ElevatorIORobot() {
        masterMotor = new TalonFX(ROBOT_ELEVATOR_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(ROBOT_ELEVATOR_FOLLOWER_MOTOR_ID);

        elevatorMasterMotorInputs = new OnyxMotorOutputs(masterMotor, ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_MASTER_MOTOR_NAME, ROTATIONS_TO_LENGTH);
        elevatorFollowerMotorInputs = new OnyxMotorOutputs(followerMotor, ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_FOLLOWER_MOTOR_NAME, ROTATIONS_TO_LENGTH);

        masterMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());

        followerMotor.setControl(new Follower(ROBOT_ELEVATOR_MASTER_MOTOR_ID, MotorAlignmentValue.Aligned));

        limitSwitch = new DigitalInput(ROBOT_ELEVATOR_SWITCH_ID);
        encoder = new CANcoder(ROBOT_ELEVATOR_CANCODER_ID);

        encoder.getConfigurator().apply(getMagnetSensorConfigs());

        positionVoltage = new PositionVoltage(0);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.SyncCANcoder;
        configuration.Feedback.FeedbackRemoteSensorID = ROBOT_ELEVATOR_CANCODER_ID;

        configuration.Slot0 = ROBOT_ELEVATOR_PID_VALUES.pidValuesToSlot0Configs();

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_FORWARD_SOFT_LIMIT_METERS);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_REVERSE_SOFT_LIMIT_METERS);

        return configuration;
    }

    public MagnetSensorConfigs getMagnetSensorConfigs() {
        MagnetSensorConfigs configs = new MagnetSensorConfigs();

        configs.withMagnetOffset(ROBOT_ELEVATOR_CANCODER_OFFSET);

        configs.withSensorDirection(SensorDirectionValue.Clockwise_Positive);

        return configs;
    }

    @Override
    public void updateInputs(ElevatorInputs inputs) {
        elevatorMasterMotorInputs.updateInputs();
        elevatorFollowerMotorInputs.updateInputs();

        inputs.elevatorMasterMotorInputs = elevatorMasterMotorInputs;
        inputs.elevatorFollowerMotorInputs = elevatorFollowerMotorInputs;

        inputs.isSwitchPressed = limitSwitch.get();
        inputs.encoderPosition = encoder.getPosition().getValueAsDouble();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }

    @Override
    public void moveToPosition(double position) {
        masterMotor.setControl(positionVoltage.withPosition(position));
    }

    @Override
    public void updatePID(PIDValues pidValues) {
        pidValues.updatePIDValues(masterMotor);
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
