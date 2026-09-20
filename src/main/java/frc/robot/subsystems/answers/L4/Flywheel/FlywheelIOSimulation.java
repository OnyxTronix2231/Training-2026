package frc.robot.subsystems.answers.L4.Flywheel;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.answers.L4.Flywheel.FlywheelConstants.*;

public class FlywheelIOSimulation implements FlywheelIO {
    private TalonFX motor;

    private DCMotorSim simulatedMotor;

    private OnyxMotorInputs flywheelMasterMotorInputs;
    private OnyxMotorInputs flywheelFollowerMotorInputs;
    
    public FlywheelIOSimulation() {
        motor = new TalonFX(SIMULATION_FLYWHEEL_MASTER_MOTOR_ID);
        
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_FLYWHEEL_NUM_OF_MOTORS),
            SingleJointedArmSim.estimateMOI(SIMULATION_FLYWHEEL_LENGTH_METERS, SIMULATION_FLYWHEEL_MASS_KG), 1),
            DCMotor.getKrakenX60(SIMULATION_FLYWHEEL_NUM_OF_MOTORS));

        motor.getConfigurator().apply(getTalonFXConfiguration());

        flywheelMasterMotorInputs = new OnyxMotorInputs(motor, FLYWHEEL_SUBSYSTEM_NAME, FLYWHEEL_MASTER_MOTOR_NAME);
        flywheelFollowerMotorInputs = new OnyxMotorInputs();
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Coast;

        return configuration;
    }

    @Override
    public void updateInputs(FlywheelInputs inputs) {
        updateMotor(motor, simulatedMotor);

        flywheelMasterMotorInputs.updateInputs();
        inputs.flywheelMasterMotorInputs = flywheelMasterMotorInputs;

        flywheelFollowerMotorInputs.updateInputs();
        inputs.flywheelFollowerMotorInputs = flywheelFollowerMotorInputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    public OnyxMotorInputs getMotorInputs() {
        return flywheelMasterMotorInputs;
    }
}
