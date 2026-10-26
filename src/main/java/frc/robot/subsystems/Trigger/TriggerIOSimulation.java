package frc.robot.subsystems.Trigger;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.subsystems.Conveyance.ConveyanceIO;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.*;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.SIMULATION_CONVEYANCE_MASS_KG;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.SIMULATION_CONVEYANCE_NUM_OF_MOTOR;
import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_MOTOR_NAME;
import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerSimulationConstants.*;

public class TriggerIOSimulation implements TriggerIO{
    private final TalonFX motor;
    private final DCMotorSim simulateMotor;
    private final OnyxMotorOutputs onyxMotorOutputs;

    public TriggerIOSimulation() {
        motor = new TalonFX(SIMULATION_CONVEYANCE_MOTOR_ID);
        simulateMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_TRIGGER_NUM_OF_MOTOR),
                SingleJointedArmSim.estimateMOI(SIMULATION_TRIGGER_LENGTH_METERS, SIMULATION_TRIGGER_MASS_KG), 1),
                DCMotor.getKrakenX60(SIMULATION_TRIGGER_NUM_OF_MOTOR));
        this.onyxMotorOutputs= new OnyxMotorOutputs(motor, TRIGGER_SUBSYSTEM_NAME, TRIGGER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);

    }


    @Override
    public void updateOutputs(TriggerOutputs outputs) {
        updateMotor(motor,simulateMotor);

        onyxMotorOutputs.updateOutputs();
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

