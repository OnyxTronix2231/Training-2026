package frc.robot.subsystems.BallIntake;

import com.ctre.phoenix6.hardware.TalonFX;

import static frc.robot.subsystems.BallIntake.BallIntakeConstants.BallIntakeRobotConstants.ROBOT_BALL_INTAKE_MOTOR_ID;

public class BallIntakeIORobot {

    private final TalonFX motor;

    public BallIntakeIORobot() {
        motor = new TalonFX(ROBOT_BALL_INTAKE_MOTOR_ID);
    }
    
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}
