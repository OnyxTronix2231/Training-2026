package frc.robot.subsystems.lecture.L4.Shooter;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.OnyxMotorOutputs.CanBus;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.lecture.L4.Shooter.ShooterConstants.*;
import static frc.robot.subsystems.lecture.L4.Shooter.ShooterConstants.ShooterSimulationConstants.*;

public class ShooterIOSimulation implements ShooterIO {
    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorOutputs shooterMotorInputs;

    public ShooterIOSimulation() {
        motor = new TalonFX(SIMULATION_SHOOTER_MASTER_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_SHOOTER_NUM_OF_MOTORS),
            SingleJointedArmSim.estimateMOI(SIMULATION_SHOOTER_LENGTH_METERS, SIMULATION_SHOOTER_MASS_KG), 1),
            DCMotor.getKrakenX60(SIMULATION_SHOOTER_NUM_OF_MOTORS));

        shooterMotorInputs = new OnyxMotorOutputs(motor, SHOOTER_SUBSYSTEM_NAME, SHOOTER_MASTER_MOTOR_NAME, CanBus.RIO);
    }

    @Override
    public void updateInputs(ShooterInputs shooterInputs) {
        updateMotor(motor, simulatedMotor);

        shooterMotorInputs.updateOutputs();
        shooterInputs.shooterMasterMotorInputs = shooterMotorInputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public double getRPS() {
        return shooterMotorInputs.getMotorAngularVelocityRotPerSec();
    }
}
