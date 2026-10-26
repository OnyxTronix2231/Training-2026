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
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.*;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceRobotConstants.ROBOT_Conveyance_SUPPLY_LIMIT;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;

public class ConveyanceIOSimulation implements ConveyanceIO {
    private final TalonFX motor;
    private final DCMotorSim simulateMotor;
    private final OnyxMotorOutputs conveyanceMasterMotorOutputs;
    private final OnyxMotorOutputs conveyanceFollowerMotorOutputs;


    public ConveyanceIOSimulation() {
        motor = new TalonFX(SIMULATION_CONVEYANCE_MOTOR_ID);
        simulateMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTOR),
                SingleJointedArmSim.estimateMOI(SIMULATION_CONVEYANCE_LENGTH_METERS, SIMULATION_CONVEYANCE_MASS_KG), 1),
                DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTOR));
        this.conveyanceMasterMotorOutputs = new OnyxMotorOutputs(motor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        this.conveyanceFollowerMotorOutputs = new OnyxMotorOutputs(motor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_FOLLOWER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        motor.getConfigurator().apply(getTalonFXConfiguration());
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
    public void updateOutputs(ConveyanceOutputs outputs) {
        updateMotor(motor, simulateMotor);

        conveyanceMasterMotorOutputs.updateOutputs();
        outputs.conveyanceFollowerMotorOutputs = conveyanceMasterMotorOutputs;
    }

    @Override
    public double getRPS() {
        return conveyanceMasterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}
