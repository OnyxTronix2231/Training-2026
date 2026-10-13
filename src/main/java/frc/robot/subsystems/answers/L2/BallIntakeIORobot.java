package frc.robot.subsystems.answers.L2;

import com.ctre.phoenix6.hardware.TalonFX;

import static frc.robot.subsystems.answers.L2.BallIntakeConstants.*;

public class BallIntakeIORobot {
    private final TalonFX motor;

    public BallIntakeIORobot() {
        motor = new TalonFX(ROBOT_BALLINTAKE_MOTOR_ID);
    }

    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}
