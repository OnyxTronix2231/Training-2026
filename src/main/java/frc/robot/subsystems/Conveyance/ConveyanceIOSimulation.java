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
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.CONVEYANCE_NAME;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceRobotAConstants.CONVEYANCE_FOLLOWER_MOTOR_NAME;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceRobotAConstants.CONVEYANCE_MASTER_MOTOR_NAME;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;


public class ConveyanceIOSimulation implements ConveyanceIO {

    private final TalonFX motor;
    private final DCMotorSim simMotor;

    private final OnyxMotorOutputs conveyanceMasterMotorOutputs;
    private final OnyxMotorOutputs conveyanceFollowerMotorOutputs;

    public ConveyanceIOSimulation() {
        motor = new TalonFX(CONVEYANCE_MASTER_MOTOR_ID);
        simMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_CONVEYANCE_LENGTH_METERS, SIMULATION_CONVEYANCE_MASS_KG), SIMULATION_CONVEYANCE_GEARING), DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTORS));

        conveyanceMasterMotorOutputs = new OnyxMotorOutputs(motor, CONVEYANCE_NAME, CONVEYANCE_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        conveyanceFollowerMotorOutputs = new OnyxMotorOutputs(motor, CONVEYANCE_NAME, CONVEYANCE_FOLLOWER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);

        motor.getConfigurator().apply(getMotorConfiguration());
    }

    public TalonFXConfiguration getMotorConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = ConveyanceConstants.ConveyanceRobotAConstants.CONVEYANCE_SUPPLY_LIMIT;

        return configuration;
    }

    @Override
    public void updateOutputs(ConveyanceOutputs conveyanceOutputs) {
        updateMotor(motor, simMotor);

        conveyanceMasterMotorOutputs.updateOutputs();
        conveyanceFollowerMotorOutputs.updateOutputs();

        conveyanceOutputs.masterMotorOutputs = conveyanceMasterMotorOutputs;
        conveyanceOutputs.followerMotorOutputs = conveyanceFollowerMotorOutputs;
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
        return conveyanceMasterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
