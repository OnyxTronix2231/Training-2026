package frc.robot.subsystems.ConveyanceIO;

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
import static frc.robot.subsystems.ConveyanceIO.ConveyanceConstants.ConveyanceSimulationConstants.*;
import static frc.robot.subsystems.ConveyanceIO.ConveyanceConstants.FOLLOWER_Motor_Name;
import static frc.robot.subsystems.ConveyanceIO.ConveyanceConstants.MASTER_Motor_Name;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.*;
import frc.robot.subsystems.ConveyanceIO.ConveyanceIO;
public class ConveyanceIOSimulation implements ConveyanceIO {

    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorOutputs conveyanceMasterMotorOutputs;
    private final OnyxMotorOutputs conveyanceFollowerMotorOutputs;

    public ConveyanceIOSimulation() {
        motor = new TalonFX(SIMULATION_CONVEYANCE_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_CONVEYANCE_LENGTH_METERS, SIMULATION_CONVEYANCE_MASS_KG), 1),
                DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTORS));

        conveyanceMasterMotorOutputs = new OnyxMotorOutputs(motor, SHOOTER_SUBSYSTEM_NAME, MASTER_Motor_Name, OnyxMotorOutputs.CanBus.RIO);
        conveyanceFollowerMotorOutputs = new OnyxMotorOutputs(motor, SHOOTER_SUBSYSTEM_NAME, FOLLOWER_Motor_Name, OnyxMotorOutputs.CanBus.RIO);
        conveyanceMasterMotorOutputs.updateOutputs();
        conveyanceFollowerMotorOutputs.updateOutputs();

        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {

        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }




    @Override
    public void updateOutputs(ConveyanceOutputs conveyanceOutputs) {
        updateMotor(motor, simulatedMotor);

        conveyanceMasterMotorOutputs.updateOutputs();
        conveyanceOutputs.masterMotorOutput= conveyanceMasterMotorOutputs;

        conveyanceFollowerMotorOutputs.updateOutputs();
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



