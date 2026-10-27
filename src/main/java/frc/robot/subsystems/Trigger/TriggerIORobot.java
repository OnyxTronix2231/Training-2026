package frc.robot.subsystems.Trigger;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_MOTOR_NAME;
import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerRobotConstants.TRIGGER_MOTOR_ID;

public class TriggerIORobot implements TriggerIO {
    private final TalonFX motor;
    private final OnyxMotorOutputs onyxMotorOutputs;

    public TriggerIORobot() {
        motor = new TalonFX(TRIGGER_MOTOR_ID);
        this.onyxMotorOutputs = new OnyxMotorOutputs(motor, TRIGGER_SUBSYSTEM_NAME, TRIGGER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);

        motor.getConfigurator().apply(getTalonFXConfiguration());

    }

    public TalonFXConfiguration getTalonFXConfiguration() {

        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }


    @Override
    public void updateOutputs(TriggerOutputs outputs) {
        onyxMotorOutputs.updateOutputs();
        outputs.triggerMotorOutputs = onyxMotorOutputs;
    }

    @Override
    public double getRPS() {
        return onyxMotorOutputs.getMotorAngularVelocityRotPerSec();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}
