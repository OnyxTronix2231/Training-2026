package frc.robot.subsystems.BallIntake;

import com.ctre.phoenix6.hardware.TalonFX;

import static frc.robot.subsystems.BallIntake.BallIntakeConstants.BallIntakeRobotConstants.BALL_INTAKE_MOTOR_ID;

public class BallIntakeIRobot {

    private final TalonFX motor;

    public BallIntakeIRobot() {
        motor = new TalonFX(BALL_INTAKE_MOTOR_ID);
    }

    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

}
