package frc.robot.subsystems.lecture.shooter;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.OnyxMotorOutputs.CanBus;

import static frc.robot.subsystems.lecture.shooter.ShooterConstants.*;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.ShooterRobotAConstants.SHOOTER_FOLLOWER_MOTOR_ID;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.ShooterRobotAConstants.SHOOTER_MASTER_MOTOR_ID;


public class ShooterIORobotA implements ShooterIO {

    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final OnyxMotorOutputs masterMotorOutputs;
    private final OnyxMotorOutputs followerMotorOutputs;

    public ShooterIORobotA() {
        masterMotor = new TalonFX(SHOOTER_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(SHOOTER_FOLLOWER_MOTOR_ID);

        masterMotorOutputs = new OnyxMotorOutputs(masterMotor, SHOOTER_SUBSYSTEM_NAME, SHOOTER_MASTER_MOTOR_NAME, CanBus.RIO);
        followerMotorOutputs = new OnyxMotorOutputs(followerMotor, SHOOTER_SUBSYSTEM_NAME, SHOOTER_FOLLOWER_MOTOR_NAME, CanBus.RIO);

        masterMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());

        followerMotor.setControl(new Follower(SHOOTER_MASTER_MOTOR_ID, MotorAlignmentValue.Opposed));
    }

    public TalonFXConfiguration getTalonFXConfiguration() {

        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }

    @Override
    public void updateOutputs(ShooterOutputs shooterOutputs) {
        masterMotorOutputs.updateOutputs();
        shooterOutputs.masterMotorOutputs = masterMotorOutputs;

        followerMotorOutputs.updateOutputs();
        shooterOutputs.followerMotorOutputs = followerMotorOutputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {
        masterMotor.setNeutralMode(neutralMode);
        followerMotor.setNeutralMode(neutralMode);
    }

    @Override
    public double getRPS() {
        return masterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }

}
