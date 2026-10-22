package frc.robot.subsystems.answers.L4.Conveyance;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.OnyxMotorOutputs.CanBus;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.answers.L4.Conveyance.ConveyanceConstants.*;
import static frc.robot.subsystems.answers.L4.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;

public class ConveyanceIOSimulation implements ConveyanceIO {
    private final TalonFX motor;

    private final DCMotorSim simulatedMotor;

    private final OnyxMotorOutputs conveyanceMasterMotorOutputs;
    private final OnyxMotorOutputs conveyanceFollowerMotorOutputs;

    public ConveyanceIOSimulation() {
        motor = new TalonFX(SIMULATION_CONVEYANCE_MASTER_MOTOR_ID);
        
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTORS),
            SingleJointedArmSim.estimateMOI(SIMULATION_CONVEYANCE_LENGTH_METERS, SIMULATION_CONVEYANCE_MASS_KG), 1),
            DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTORS));

        motor.getConfigurator().apply(getTalonFXConfiguration());

        conveyanceMasterMotorOutputs = new OnyxMotorOutputs(motor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_MASTER_MOTOR_NAME, CanBus.RIO);
        conveyanceFollowerMotorOutputs = new OnyxMotorOutputs();
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Coast;

        return configuration;
    }

    @Override
    public void updateOutputs(ConveynaceOutputs outputs) {
        updateMotor(motor, simulatedMotor);

        conveyanceMasterMotorOutputs.updateOutputs();
        outputs.conveyanceMasterMotorOutputs = conveyanceMasterMotorOutputs;

        outputs.conveyanceFollowerMotorOutputs = conveyanceFollowerMotorOutputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    public OnyxMotorOutputs getMotorOutputs() {
        return conveyanceMasterMotorOutputs;
    }
}
