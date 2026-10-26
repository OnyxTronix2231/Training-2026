package frc.robot.subsystems.Hinge;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.Hinge.HingeConstants.*;
import static frc.robot.subsystems.Hinge.HingeConstants.ShooterSimulationConstants.*;

public class HingeIOSimulation implements HingeIO{

    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorOutputs motorOutputs;

    public HingeIOSimulation() {
        motor = new TalonFX(SIMULATION_HINGE_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_HINGE_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_HINGE_LENGTH_METERS, SIMULATION_HINGE_MASS_KG), SIMULATION_HINGE_GEARING),
                DCMotor.getKrakenX60(SIMULATION_HINGE_NUM_OF_MOTORS));

        motorOutputs = new OnyxMotorOutputs(motor, HINGE_SUBSYSTEM_NAME, HINGE_MOTOR_NAME, ROTATIONS_TO_ANGLE, OnyxMotorOutputs.CanBus.RIO);

        motorOutputs.updateOutputs();
        
        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = SIMULATION_LENGTH_TO_ROTATION(HINGE_FORWARD_SOFT_LIMIT_METERS);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = SIMULATION_LENGTH_TO_ROTATION(HINGE_REVERSE_SOFT_LIMIT_METERS);

        return configuration;
    }

    @Override
    public void updateOutputs(HingeOutputs hingeOutputs) {
        updateMotor(motor, simulatedMotor);

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
        return motorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
