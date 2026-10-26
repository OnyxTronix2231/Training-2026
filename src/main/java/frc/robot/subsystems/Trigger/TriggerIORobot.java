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
import frc.robot.subsystems.Conveyance.ConveyanceIO;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceRobotConstants.ROBOT_Conveyance_SUPPLY_LIMIT;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.SIMULATION_CONVEYANCE_MASS_KG;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.SIMULATION_CONVEYANCE_NUM_OF_MOTOR;
import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_MOTOR_NAME;
import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;

public class TriggerIORobot implements TriggerIO{
    private final TalonFX motor;
    private final DCMotorSim simulateMotor;
    private final OnyxMotorOutputs onyxMotorOutputs;

    public TriggerIORobot() {
        motor = new TalonFX(SIMULATION_CONVEYANCE_MOTOR_ID);
        simulateMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTOR),
                SingleJointedArmSim.estimateMOI(SIMULATION_CONVEYANCE_LENGTH_METERS, SIMULATION_CONVEYANCE_MASS_KG), 1),
                DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTOR));
        this.onyxMotorOutputs= new OnyxMotorOutputs(motor, TRIGGER_SUBSYSTEM_NAME, TRIGGER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);

    }

    public TalonFXConfiguration getTalonFXConfiguration() {

        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.CurrentLimits.SupplyCurrentLimitEnable=true;

        configuration.CurrentLimits.SupplyCurrentLimit = ROBOT_Conveyance_SUPPLY_LIMIT;


        return configuration;
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
