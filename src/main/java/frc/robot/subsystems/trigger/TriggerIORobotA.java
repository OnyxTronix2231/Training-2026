package frc.robot.subsystems.trigger;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.subsystems.trigger.TriggerConstants.*;
import static frc.robot.subsystems.trigger.TriggerConstants.TriggerRobotAConstants.*;

public class TriggerIORobotA implements TriggerIO{

    private final TalonFX motor;

    private final OnyxMotorOutputs motorOutputs;

    public TriggerIORobotA() {
        motor = new TalonFX(ROBOT_A_TRIGGER_MOTOR_ID);

        motorOutputs = new OnyxMotorOutputs(motor, TRIGGER_SUBSYSTEM_NAME, TRIGGER_MOTOR_NAME, OnyxMotorOutputs.CanBus.CANIVORE);

        motorOutputs.updateOutputs();

        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }

    @Override
    public void updateOutputs(TriggerOutputs triggerOutputs) {
        motorOutputs.updateOutputs();
        triggerOutputs.motorOutputs = motorOutputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {
        motor.setNeutralMode(neutralMode);
    }

    @Override
    public double getRPS() {
        return motorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
