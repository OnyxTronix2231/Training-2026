package frc.robot.subsystems.trigger;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.subsystems.trigger.TriggerConstants.TRIGGER_MOTOR_NAME;
import static frc.robot.subsystems.trigger.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.trigger.TriggerConstants.TriggerRobotConstants.ROBOT_TRIGGER_MOTOR_ID;
import static frc.robot.subsystems.trigger.TriggerConstants.TriggerRobotConstants.TRIGGER_SUPPLY_LIMIT;

public class TriggerIORobot implements TriggerIO {
    private final TalonFX motor;
    private final OnyxMotorOutputs triggerMotorOutputs;

    public TriggerIORobot() {
        motor = new TalonFX(ROBOT_TRIGGER_MOTOR_ID);
        triggerMotorOutputs = new OnyxMotorOutputs(motor, TRIGGER_SUBSYSTEM_NAME, TRIGGER_MOTOR_NAME, OnyxMotorOutputs.CanBus.CANIVORE);
        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = TRIGGER_SUPPLY_LIMIT;
        return configuration;
    }

    @Override
    public void updateOutputs(TriggerOutputs inputs) {
        triggerMotorOutputs.updateOutputs();
        inputs.triggerMotorOutputs = triggerMotorOutputs;
        inputs.triggerMotorOutputs = triggerMotorOutputs;
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
        return triggerMotorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
