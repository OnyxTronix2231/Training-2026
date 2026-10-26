package frc.robot.subsystems.Trigger;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import static frc.robot.subsystems.Trigger.TriggerConstants.*;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerRobotAConstants.TRIGGER_FOLLOWER_MOTOR_ID;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerRobotAConstants.TRIGGER_MASTER_MOTOR_ID;

public class TriggerIORobotA implements TriggerIO {
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final OnyxMotorOutputs masterMotorOutputs;
    private final OnyxMotorOutputs followerMotorOutputs;

    public TriggerIORobotA() {
        masterMotor = new TalonFX(TRIGGER_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(TRIGGER_FOLLOWER_MOTOR_ID);

        masterMotorOutputs = new OnyxMotorOutputs(masterMotor, TRIGGER_SUBSYSTEM_NAME, TRIGGER_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        followerMotorOutputs = new OnyxMotorOutputs(followerMotor, TRIGGER_SUBSYSTEM_NAME, TRIGGER_FOLLOWER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);

        masterMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());

        followerMotor.setControl(new Follower(TRIGGER_MASTER_MOTOR_ID, MotorAlignmentValue.Aligned));
    }

    public TalonFXConfiguration getTalonFXConfiguration() {

        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }

    @Override
    public void updateOutputs(TriggerIO.TriggerOutputs triggerOutputs) {
        masterMotorOutputs.updateOutputs();
        triggerOutputs.triggerMasterMotorOutputs = masterMotorOutputs;

        followerMotorOutputs.updateOutputs();
        triggerOutputs.triggerFollowerMotorOutputs = followerMotorOutputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {
        masterMotor.setNeutralMode(neutralMode);
        followerMotor.setNeutralMode(neutralMode);
    }

    @Override
    public double getRPS() {
        return masterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
