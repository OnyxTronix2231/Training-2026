package frc.robot.subsystems.answers.L4.Turret;

import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import edu.wpi.first.math.util.Units;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.answers.L4.Turret.TurretConstants.*;

public class TurretIORobot implements TurretIO {
    private final TalonFX motor;

    private final OnyxMotorInputs turretMotorInputs;

    private CANcoder encoder;

    public TurretIORobot() {
        motor = new TalonFX(TURRET_MOTOR_ID);

        turretMotorInputs = new OnyxMotorInputs(motor, TURRET_SUBSYSTEM_NAME, TURRET_MOTOR_NAME, ROTATIONS_TO_ANGLE);

        motor.getConfigurator().apply(getTalonFXConfiguration());

        encoder = new CANcoder(TURRET_ENCODER_ID);

        encoder.getConfigurator().apply(getMagnetSensorConfigs());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.Feedback.SensorToMechanismRatio = TURRET_CONVERSION_RATE;

        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = Units.degreesToRotations(TURRET_FORWARD_SOFT_LIMIT_DEGREES);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = Units.degreesToRotations(TURRET_REVERSE_SOFT_LIMIT_DEGREES);

        return configuration;
    }

    public MagnetSensorConfigs getMagnetSensorConfigs() {
        MagnetSensorConfigs configuration = new MagnetSensorConfigs();

        configuration.withMagnetOffset(TURRET_CANCODER_OFFSET);

        configuration.withSensorDirection(SensorDirectionValue.CounterClockwise_Positive);

        return configuration;
    }

    @Override
    public void updateInputs(TurretInputs inputs) {
        turretMotorInputs.updateInputs();
        inputs.turretMotorInputs = turretMotorInputs;

        inputs.encoderPosition = encoder.getPosition().getValueAsDouble();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}
