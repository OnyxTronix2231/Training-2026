package frc.robot.subsystems.Hinge;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.util.Units;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.subsystems.Hinge.HingeConstants.*;
import static frc.robot.subsystems.Hinge.HingeConstants.HingeRobotAConstants.*;

public class HingeIORobotA implements HingeIO{
    private final TalonFX motor;

    private final OnyxMotorOutputs motorOutputs;

    public HingeIORobotA() {
        motor = new TalonFX(ROBOT_A_HINGE_MOTOR_ID);

        motorOutputs = new OnyxMotorOutputs(motor, HINGE_SUBSYSTEM_NAME, HINGE_MOTOR_NAME, ROTATIONS_TO_ANGLE, OnyxMotorOutputs.CanBus.CANIVORE);

        motorOutputs.updateOutputs();

        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = Units.degreesToRotations(HINGE_FORWARD_SOFT_LIMIT);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = Units.degreesToRotations(HINGE_REVERSE_SOFT_LIMIT);

        return configuration;
    }

    @Override
    public void updateOutputs(HingeIO.HingeOutputs hingeOutputs) {

        motorOutputs.updateOutputs();
        hingeOutputs.motorOutputs = motorOutputs;

    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {
        motor.setNeutralMode(neutralMode);
    }

    @Override
    public double getAngle() {
        return motorOutputs.getValue();
    }
}
