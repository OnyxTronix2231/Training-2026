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
import static frc.robot.subsystems.Trigger.TriggerConstants.*;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerSimulationConstants.*;

public class TriggerIOSimulation implements TriggerIO {

    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorOutputs masterMotorOutputs;
    private final OnyxMotorOutputs followerMotorOutputs;

    public TriggerIOSimulation() {
        motor = new TalonFX(SIMULATION_TRIGGER_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_TRIGGER_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_TRIGGER_LENGTH_METERS, SIMULATION_TRIGGER_MASS_KG), 1),
                DCMotor.getKrakenX60(SIMULATION_TRIGGER_NUM_OF_MOTORS));

        masterMotorOutputs = new OnyxMotorOutputs(motor, TRIGGER_SUBSYSTEM_NAME, TRIGGER_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        followerMotorOutputs = new OnyxMotorOutputs(motor, TRIGGER_SUBSYSTEM_NAME, TRIGGER_FOLLOWER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);

        motor.getConfigurator().apply(getTalonFXConfiguration());
        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {

        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }
    @Override
    public void updateOutputs(TriggerIO.TriggerOutputs triggerOutputs) {
        updateMotor(motor, simulatedMotor);

        masterMotorOutputs.updateOutputs();
        triggerOutputs.triggerMasterMotorOutputs = masterMotorOutputs;

        followerMotorOutputs.updateOutputs();
        triggerOutputs.triggerFollowerMotorOutputs = followerMotorOutputs;
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
    public double getRPS() {
        return masterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }
}


