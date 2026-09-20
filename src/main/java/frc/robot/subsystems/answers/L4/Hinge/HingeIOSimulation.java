package frc.robot.subsystems.answers.L4.Hinge;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.answers.L4.Hinge.HingeConstants.*;

public class HingeIOSimulation implements HingeIO {
    private TalonFX motor;

    private DCMotorSim simulatedMotor;

    private OnyxMotorInputs hingeMotorInputs;
    
    public HingeIOSimulation() {
        motor = new TalonFX(SIMULATION_HINGE_MOTOR_ID);
        
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_HINGE_NUM_OF_MOTORS),
            SingleJointedArmSim.estimateMOI(SIMULATION_HINGE_LENGTH_METERS, SIMULATION_HINGE_MASS_KG), 1),
            DCMotor.getKrakenX60(SIMULATION_HINGE_NUM_OF_MOTORS));

        hingeMotorInputs = new OnyxMotorInputs(motor, HINGE_SUBSYSTEM_NAME, HINGE_MOTOR_NAME, ROTATIONS_TO_ANGLE);

        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = Units.degreesToRotations(SIMULATION_HINGE_FORWARD_SOFT_LIMIT_DEGREES);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = Units.degreesToRotations(SIMULATION_HINGE_REVERSE_SOFT_LIMIT_DEGREES);

        return configuration;
    }

    @Override
    public void updateInputs(HingeInputs inputs) {
        updateMotor(motor, simulatedMotor);

        hingeMotorInputs.updateInputs();
        inputs.hingeMotorInputs = hingeMotorInputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    public OnyxMotorInputs getMotorInputs() {
        return hingeMotorInputs;
    }
}
