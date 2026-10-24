package frc.robot.subsystems.lecture.shooter;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.OnyxMotorOutputs.CanBus;

import static frc.robot.subsystems.lecture.shooter.ShooterConstants.SHOOTER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.ShooterRobotBConstants.*;


public class ShooterIORobotB implements ShooterIO {

    private final TalonFX motor;

    private final OnyxMotorOutputs motorOutputs;

    public ShooterIORobotB() {
        motor = new TalonFX(SHOOTER_MOTOR_ID);

        motorOutputs = new OnyxMotorOutputs(motor, SHOOTER_SUBSYSTEM_NAME, SHOOTER_MOTOR_NAME, CanBus.RIO);

        motor.getConfigurator().apply(getTalonFXConfiguration());

    }

    public TalonFXConfiguration getTalonFXConfiguration() {

        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Coast;

        return configuration;
    }

    @Override
    public void updateOutputs(ShooterOutputs shooterOutputs) {
        motorOutputs.updateOutputs();
        shooterOutputs.masterMotorOutputs = motorOutputs;
        shooterOutputs.followerMotorOutputs = motorOutputs;
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
        return motorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
