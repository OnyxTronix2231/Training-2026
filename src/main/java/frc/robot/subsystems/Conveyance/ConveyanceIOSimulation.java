package frc.robot.subsystems.Conveyance;

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
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.CONVEYANCE_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;


public class ConveyanceIOSimulation implements ConveyanceIO{

    private final DCMotorSim simulatorMasterMotor;
    private final TalonFX motor;
    private final OnyxMotorOutputs masterMotorOutputs;
    private final OnyxMotorOutputs followerMotorOutputs;



    public ConveyanceIOSimulation() {
        motor = new TalonFX(SIMULATION_CONVEYANCE_MASTER_MOTOR_ID);
        simulatorMasterMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(0.001,0.001), 1),
                DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTORS));
        masterMotorOutputs = new OnyxMotorOutputs(motor,CONVEYANCE_SUBSYSTEM_NAME ,SIMULATION_CONVEYANCE_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        followerMotorOutputs = new OnyxMotorOutputs(motor,CONVEYANCE_SUBSYSTEM_NAME ,SIMULATION_CONVEYANCE_FOLLOWER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);



    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = SIMULATION_CONVEYANCE_SUPPLY_LIMIT;

        return configuration;
    }
    @Override
    public void updateOutputs(ConveyanceIO.ConveyanceOutputs conveyanceOutputs) {
        updateMotor(motor, simulatorMasterMotor);
        masterMotorOutputs.updateOutputs();
        ConveyanceIO.ConveyanceOutputs.masterMotorOutputs = masterMotorOutputs;

        updateMotor(motor, simulatorMasterMotor);
        followerMotorOutputs.updateOutputs();
        ConveyanceIO.ConveyanceOutputs.followerMotorOutputs = followerMotorOutputs;
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {

    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public double getRPS() {
        return masterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
