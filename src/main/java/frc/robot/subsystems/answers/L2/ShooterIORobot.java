package frc.robot.subsystems.answers.L2;

import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.answers.L2.ShooterConstants.*;

public class ShooterIORobot {
    private final TalonFX masterMotor;

    private final OnyxMotorInputs shooterMasterMotorInputs;

    public ShooterIORobot() {
        masterMotor = new TalonFX(ROBOT_SHOOTER_MASTER_MOTOR_ID);

        shooterMasterMotorInputs = new OnyxMotorInputs(masterMotor, SHOOTER_SUBSYSTEM_NAME, SHOOTER_MOTOR_NAME);

        shooterMasterMotorInputs.updateInputs();
    }

    public void updateInputs() {
        shooterMasterMotorInputs.updateInputs();
    }

    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }
}
