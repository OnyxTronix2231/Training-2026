package frc.robot.subsystems.answers.L6;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.OnyxMotorOutputs.CanBus;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.answers.L5.TriggerConstants.TRIGGER_MOTOR_NAME;
import static frc.robot.subsystems.answers.L5.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.answers.L5.TriggerConstants.TriggerSimulationConstants.*;
import static frc.robot.subsystems.answers.L6.TriggerConstants.TriggerSimulationConstants.SIMULATION_TRIGGER_DEBOUNCE_TIME;

public class TriggerIOSimulation implements TriggerIO {
    private TalonFX motor;

    private DCMotorSim simulatedMotor;

    private OnyxMotorOutputs triggerMotorInputs;

    private boolean isSensorSeeing;

    private Debouncer sensorDebouncer;

    public TriggerIOSimulation() {
        motor = new TalonFX(SIMULATION_TRIGGER_MOTOR_ID);

        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_TRIGGER_NUM_OF_MOTORS),
            SingleJointedArmSim.estimateMOI(SIMULATION_TRIGGER_LENGTH_METERS, SIMULATION_TRIGGER_MASS_KG), 1),
            DCMotor.getKrakenX60(SIMULATION_TRIGGER_NUM_OF_MOTORS));

        triggerMotorInputs = new OnyxMotorOutputs(motor, TRIGGER_SUBSYSTEM_NAME, TRIGGER_MOTOR_NAME, CanBus.CANIVORE);

        motor.getConfigurator().apply(getTalonFXConfiguration());

        isSensorSeeing = false;

        sensorDebouncer = new Debouncer(SIMULATION_TRIGGER_DEBOUNCE_TIME);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        return configuration;
    }

    @Override
    public void updateInputs(TriggerInputs inputs) {
        updateMotor(motor, simulatedMotor);

        triggerMotorInputs.updateOutputs();
        inputs.triggerMotorInputs = triggerMotorInputs;

        inputs.isSensorSeeing = sensorDebouncer.calculate(isSensorSeeing);
    }

    public void setSensorValue(boolean value) {
        isSensorSeeing = value;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}
