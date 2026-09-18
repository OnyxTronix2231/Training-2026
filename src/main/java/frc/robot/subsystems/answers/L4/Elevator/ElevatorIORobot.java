package frc.robot.subsystems.answers.L4.Elevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.answers.L4.Elevator.ElevatorConstants.*;

public class ElevatorIORobot implements ElevatorIO{
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final OnyxMotorInputs elevatorMasterMotorInputs;
    private final OnyxMotorInputs elevatorFollowerMotorInputs;

    private final DigitalInput elevatorSwitch;

    public ElevatorIORobot() {
        masterMotor = new TalonFX(ELEVATOR_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(ELEVATOR_FOLLOWER_MOTOR_ID);

        elevatorMasterMotorInputs = new OnyxMotorInputs(masterMotor, ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_MASTER_MOTOR_NAME);
        elevatorFollowerMotorInputs = new OnyxMotorInputs(followerMotor, ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_FOLLOWER_MOTOR_NAME);

        masterMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());

        followerMotor.setControl(new Follower(ELEVATOR_MASTER_MOTOR_ID, MotorAlignmentValue.Aligned));

        elevatorSwitch = new DigitalInput(ELEVATOR_SWITCH_ID);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_FORWARD_SOFT_LIMIT_METERS, false);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_REVERSE_SOFT_LIMIT_METERS, false);

        return configuration;
    }

    @Override
    public void updateInputs(ElevatorInputs inputs) {
        elevatorMasterMotorInputs.updateInputs();
        inputs.elevatorFollowerMotorInputs = elevatorFollowerMotorInputs;

        elevatorFollowerMotorInputs.updateInputs();
        inputs.elevatorFollowerMotorInputs = elevatorFollowerMotorInputs;

        inputs.isSwitchPressed = elevatorSwitch.get();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }
}
