package frc.robot.subsystems.answers.L4.Turret;

import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.answers.L4.Turret.TurretConstants.*;

public class TurretIOSimulation implements TurretIO {
    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorInputs turretMotorInputs;

    private CANcoder encoder;

    public TurretIOSimulation() {
        motor = new TalonFX(TURRET_MOTOR_ID);

        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_TURRET_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_TURRET_LENGTH_METERS, SIMULATION_TURRET_MASS_KG), SIMULATION_TURRET_CONVERSION_RATE),
            DCMotor.getKrakenX60(SIMULATION_TURRET_NUM_OF_MOTORS));

        turretMotorInputs = new OnyxMotorInputs(motor, TURRET_SUBSYSTEM_NAME, TURRET_MOTOR_NAME, ROTATIONS_TO_ANGLE);

        motor.getConfigurator().apply(getTalonFXConfiguration());

        encoder = new CANcoder(TURRET_ENCODER_ID);

        encoder.getConfigurator().apply(getMagnetSensorConfigs());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = Units.degreesToRotations(SIMULATION_TURRET_FORWARD_SOFT_LIMIT_DEGREES);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = Units.degreesToRotations(SIMULATION_TURRET_REVERSE_SOFT_LIMIT_DEGREES);

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
