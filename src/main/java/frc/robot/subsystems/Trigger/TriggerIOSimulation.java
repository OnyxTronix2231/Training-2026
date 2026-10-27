package frc.robot.subsystems.Trigger;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.ConveyanceIO.ConveyanceConstants.*;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerSimulationConstants.*;

public class TriggerIOSimulation implements TriggerIO {
    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorOutputs triggerMasterMotorOutputs;
    private final OnyxMotorOutputs triggerFollowerMotorOutputs;

    public TriggerIOSimulation() {
        motor = new TalonFX(SIMULATION_TRIGGER_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_TRIGGER_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_TRIGGER_LENGTH_METERS, SIMULATION_TRIGGER_MASS_KG), 1),
                DCMotor.getKrakenX60(SIMULATION_TRIGGER_NUM_OF_MOTORS));

        triggerMasterMotorOutputs = new OnyxMotorOutputs(motor, SUBSYSTEM, MASTER_Motor_Name, OnyxMotorOutputs.CanBus.RIO);
        triggerFollowerMotorOutputs = new OnyxMotorOutputs(motor, SUBSYSTEM, FOLLOWER_Motor_Name, OnyxMotorOutputs.CanBus.RIO);
        triggerMasterMotorOutputs.updateOutputs();
        triggerFollowerMotorOutputs.updateOutputs();

        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {

        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }


    public void updateOutputs(HingeOutputs TriggerOutput) {
        updateMotor(motor, simulatedMotor);

        triggerMasterMotorOutputs.updateOutputs();
        TriggerOutput.TriggerMotorOutput = triggerMasterMotorOutputs;

        triggerFollowerMotorOutputs.updateOutputs();
        TriggerOutput.TriggerMotorOutput = triggerFollowerMotorOutputs;

    }


    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }


    public void setNeutralMode(NeutralModeValue neutralMode) {
        motor.setNeutralMode(neutralMode);
    }


    public double getRPS() {
        return triggerMasterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }
}






