package frc.robot.subsystems.answers.L2;

import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.answers.L2.BallIntakeConstants.*;

public class BallIntakeIORobot {
    private final TalonFX motor;

    private final OnyxMotorInputs ballIntakeMotorInputs;

    public BallIntakeIORobot() {
        motor = new TalonFX(ROBOT_BALLINTAKE_MOTOR_ID);

        ballIntakeMotorInputs = new OnyxMotorInputs(motor, BALLINTAKE_SUBSYSTEM_NAME, BALLINTAKE_MOTOR_NAME);

        ballIntakeMotorInputs.updateInputs();
    }

    public void updateInputs() {
        ballIntakeMotorInputs.updateInputs();
    }

    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}
