package frc.robot.subsystems.lecture;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.TalonFX;

import static frc.robot.subsystems.lecture.ShooterConstants.ShooterRobotConstants.ROBOT_SHOOTER_MOTOR_ID;

public class ShooterIORobot {

    private final TalonFX motor;

    public ShooterIORobot() {
        motor = new TalonFX(ROBOT_SHOOTER_MOTOR_ID, new CANBus("DriveTrain"));
    }

    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}
