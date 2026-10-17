package frc.robot.subsystems.Ballintake;

import com.ctre.phoenix6.hardware.TalonFX;

import static frc.robot.subsystems.Ballintake.BallIntakeConstants.ROBOT_MOTOR_ID;

public class BallIntakeIORobot {

    public final TalonFX motor;

    public BallIntakeIORobot() {
        motor = new TalonFX(ROBOT_MOTOR_ID);
    }

    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);

    }
}