package frc.robot.subsystems.Trigger;

import com.ctre.phoenix6.CANBus;
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
import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerSimulationConstants.*;

public class TriggerIOSimulation implements TriggerIO {

    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorOutputs triggerMotorOutputs;

    public TriggerIOSimulation() {
        motor = new TalonFX(TRIGGER_MOTOR_ID, new CANBus(TRIGGER_CAN_BUS));
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_TRIGGER_LENGTH_METERS, SIMULATION_TRIGGER_MASS_KG), SIMULATION_TRIGGER_GEARING), DCMotor.getKrakenX60(SIMULATION_NUM_OF_MOTORS));

        triggerMotorOutputs = new OnyxMotorOutputs(motor, TRIGGER_SUBSYSTEM_NAME, TRIGGER_MOTOR_NAME, OnyxMotorOutputs.CanBus.CANIVORE);

        motor.getConfigurator().apply(getMotorConfiguration());

    }

    public TalonFXConfiguration getMotorConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }

    @Override
    public void updateOutputs(TriggerOutputs triggerOutputs) {
        updateMotor(motor, simulatedMotor);

        triggerMotorOutputs.updateOutputs();

        triggerOutputs.motorOutputs = triggerMotorOutputs;
    }


    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public double getRPS() {
        return triggerMotorOutputs.getMotorAngularVelocityRotPerSec();
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {
        motor.setNeutralMode(neutralMode);
    }

}
