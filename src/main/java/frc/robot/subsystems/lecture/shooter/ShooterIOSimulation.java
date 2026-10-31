package frc.robot.subsystems.lecture.shooter;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.logging.motorSimulation.SimulatedSensors;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.*;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.ShooterSimulationConstants.*;

public class ShooterIOSimulation implements ShooterIO {

    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorOutputs shooterMasterMotorOutputs;
    private final OnyxMotorOutputs shooterFollowerMotorOutputs;

    public ShooterIOSimulation() {
        motor = new TalonFX(SIMULATION_SHOOTER_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_SHOOTER_NUM_OF_MOTORS),
            SingleJointedArmSim.estimateMOI(SIMULATION_SHOOTER_LENGTH_METERS, SIMULATION_SHOOTER_MASS_KG), 1),
            DCMotor.getKrakenX60(SIMULATION_SHOOTER_NUM_OF_MOTORS));

        shooterMasterMotorOutputs = new OnyxMotorOutputs(motor, SHOOTER_SUBSYSTEM_NAME, SHOOTER_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        shooterFollowerMotorOutputs = new OnyxMotorOutputs(motor, SHOOTER_SUBSYSTEM_NAME, SHOOTER_FOLLOWER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);

        shooterMasterMotorOutputs.updateOutputs();
        shooterFollowerMotorOutputs.updateOutputs();

        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {

        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }

    @Override
    public void updateOutputs(ShooterOutputs shooterOutputs) {
        updateMotor(motor, simulatedMotor);

        shooterMasterMotorOutputs.updateOutputs();
        shooterOutputs.masterMotorOutputs = shooterMasterMotorOutputs;

        shooterFollowerMotorOutputs.updateOutputs();
        shooterOutputs.followerMotorOutputs = shooterFollowerMotorOutputs;

        // There is no real sensor in simulation, so the value comes from SimulatedSensors
        shooterOutputs.isSensorSeeing = SimulatedSensors.isSimulatedShooterSensorSeeing();
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
        return shooterMasterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
