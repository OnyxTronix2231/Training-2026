package frc.robot.subsystems.answers.L4.Conveyance;

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
import static frc.robot.subsystems.answers.L4.Conveyance.ConveyanceConstants.*;
import static frc.robot.subsystems.answers.L4.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;

public class ConveyanceIOSimulation implements ConveyanceIO {
    private TalonFX motor;

    private DCMotorSim simulatedMotor;

    private OnyxMotorInputs conveyanceMasterMotorInputs;
    private OnyxMotorInputs conveyanceFollowerMotorInputs;

    public ConveyanceIOSimulation() {
        motor = new TalonFX(SIMULATION_CONVEYANCE_MASTER_MOTOR_ID);
        
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTORS),
            SingleJointedArmSim.estimateMOI(SIMULATION_CONVEYANCE_LENGTH_METERS, SIMULATION_CONVEYANCE_MASS_KG), 1),
            DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTORS));

        motor.getConfigurator().apply(getTalonFXConfiguration());

        conveyanceMasterMotorInputs = new OnyxMotorInputs(motor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_MASTER_MOTOR_NAME);
        conveyanceFollowerMotorInputs = new OnyxMotorInputs();
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Coast;

        return configuration;
    }

    @Override
    public void updateInputs(ConveynaceInputs inputs) {
        updateMotor(motor, simulatedMotor);

        conveyanceMasterMotorInputs.updateInputs();
        inputs.conveyanceMasterMotorInputs = conveyanceMasterMotorInputs;

        inputs.conveyanceFollowerMotorInputs = conveyanceFollowerMotorInputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    public OnyxMotorInputs getMotorInputs() {
        return conveyanceMasterMotorInputs;
    }
}
