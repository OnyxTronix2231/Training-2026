package frc.robot.subsystems.lecture.L4.Shooter;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.subsystems.lecture.L4.Shooter.ShooterConstants.*;
import static frc.robot.subsystems.lecture.L4.Shooter.ShooterConstants.ShooterRobotConstants.ROBOT_SHOOTER_MOTOR_ID;

public class ShooterIORobot implements ShooterIO {
    private final TalonFX motor;

    private final OnyxMotorOutputs shooterMasterMotorInputs;

    public ShooterIORobot() {
        motor = new TalonFX(ROBOT_SHOOTER_MOTOR_ID, new CANBus("DriveTrain"));

        shooterMasterMotorInputs = new OnyxMotorOutputs(motor, SHOOTER_SUBSYSTEM_NAME, SHOOTER_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.CANIVORE);
    }

    @Override
    public void updateInputs(ShooterInputs shooterInputs) {
        shooterMasterMotorInputs.updateInputs();
        shooterInputs.shooterMasterMotorInputs = shooterMasterMotorInputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public double getRPS() {
        return shooterMasterMotorInputs.getMotorAngularVelocityRotPerSec();
    }
}
