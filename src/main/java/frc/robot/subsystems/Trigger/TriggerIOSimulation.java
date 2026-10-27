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
import static frc.robot.subsystems.Hinge.HingeConstants.HingeSimulationConstants.SIMULATION_HINGE_MOTOR_ID;
import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_MOTOR_NAME;
import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerSimulationConstants.*;

public class TriggerIOSimulation implements TriggerIO{
    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;
    private final OnyxMotorOutputs onyxMotorOutputs;

    public TriggerIOSimulation() {
        motor = new TalonFX(SIMULATION_HINGE_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_TRIGGER_NUM_OF_MOTOR),
                SingleJointedArmSim.estimateMOI(SIMULATION_TRIGGER_LENGTH_METERS, SIMULATION_TRIGGER_MASS_KG), 1),
                DCMotor.getKrakenX60(SIMULATION_TRIGGER_NUM_OF_MOTOR));
        this.onyxMotorOutputs= new OnyxMotorOutputs(motor, TRIGGER_SUBSYSTEM_NAME, TRIGGER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {

        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }

    @Override
    public void updateOutputs(TriggerOutputs outputs) {
        updateMotor(motor, simulatedMotor);

        onyxMotorOutputs.updateOutputs();
        outputs.triggerMotorOutputs = onyxMotorOutputs;
    }

    @Override
    public double getRPS() {
        return onyxMotorOutputs.getMotorAngularVelocityRotPerSec();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}

