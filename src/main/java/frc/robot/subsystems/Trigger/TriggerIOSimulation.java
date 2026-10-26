package frc.robot.subsystems.Trigger;

import TrainingUtils.KeyButton;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.subsystems.Conveyance.ConveyanceIO;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.SIMULATION_CONVEYANCE_SUPPLY_LIMIT;
import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerSimulationConstants.*;

public class TriggerIOSimulation implements TriggerIO{

    private final TalonFX motor;
    private final DCMotorSim simMotor;
    private final OnyxMotorOutputs motorOutputs;

    public TriggerIOSimulation() {
        motor = new TalonFX(SIMULATION_TRIGGER_MOTOR_ID);
        simMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_TRIGGER_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(0.001,0.001), 1),
                DCMotor.getKrakenX60(SIMULATION_TRIGGER_NUM_OF_MOTORS));
        motorOutputs = new OnyxMotorOutputs(motor, TRIGGER_SUBSYSTEM_NAME,SIMULATION_TRIGGER_MOTOR_NAME, OnyxMotorOutputs.CanBus.CANIVORE);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;;

        return configuration;
    }

    @Override
    public void updateOutputs(TriggerIO.TriggerOutputs triggerOutputs) {
        updateMotor(motor, simMotor);
        motorOutputs.updateOutputs();
        TriggerOutputs.motorOutputs = motorOutputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {};

    public double getRPS() {
        return motorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
