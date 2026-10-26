package frc.robot.subsystems.Trigger;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerRobotAConstants.TRIGGER_MOTOR_ID;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerRobotAConstants.TRIGGER_MOTOR_NAME;

public class TriggerIORobot implements TriggerIO{

    private final TalonFX motor;
    private final OnyxMotorOutputs motorOutputs;

    public TriggerIORobot() {
        motor = new TalonFX(TRIGGER_MOTOR_ID);
        motorOutputs = new OnyxMotorOutputs(motor, TRIGGER_SUBSYSTEM_NAME,TRIGGER_MOTOR_NAME, OnyxMotorOutputs.CanBus.CANIVORE);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;;

        return configuration;
    }

    @Override
    public void updateOutputs(TriggerIO.TriggerOutputs triggerOutputs) {
        motorOutputs.updateOutputs();
        TriggerIO.TriggerOutputs.motorOutputs = motorOutputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {};

    public double getRPS() {
        return motorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
