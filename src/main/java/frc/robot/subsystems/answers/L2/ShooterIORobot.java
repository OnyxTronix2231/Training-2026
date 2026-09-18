package frc.robot.subsystems.answers.L2;

import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.answers.L2.ShooterConstants.*;

public class ShooterIORobot {
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final OnyxMotorInputs shooterMasterMotorInputs;
    private final OnyxMotorInputs shooterFollowerMotorInputs;


    public ShooterIORobot() {
        masterMotor = new TalonFX(ROBOT_SHOOTER_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(ROBOT_SHOOTER_FOLLOWER_MOTOR_ID);

        shooterMasterMotorInputs = new OnyxMotorInputs(masterMotor, SHOOTER_SUBSYSTEM_NAME, SHOOTER_MASTER_MOTOR_NAME, OnyxMotorInputs.CanBus.CANIVORE);
        shooterFollowerMotorInputs = new OnyxMotorInputs(followerMotor, SHOOTER_SUBSYSTEM_NAME, SHOOTER_FOLLOWER_MOTOR_NAME, OnyxMotorInputs.CanBus.CANIVORE);

        shooterMasterMotorInputs.updateInputs();
        shooterFollowerMotorInputs.updateInputs();
    }

    public void updateInputs() {
        shooterMasterMotorInputs.updateInputs();
        shooterFollowerMotorInputs.updateInputs();
    }

    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
        followerMotor.set(dutyCycle);
    }
}
