package frc.robot.subsystems.answers.L5;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.answers.L5.TriggerConstants.TRIGGER_MOTOR_NAME;
import static frc.robot.subsystems.answers.L5.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.answers.L5.TriggerConstants.TriggerRobotConstants.ROBOT_TRIGGER_MOTOR_ID;

public class TriggerIORobot implements TriggerIO {
    private TalonFX motor;

    private OnyxMotorInputs triggerMotorInputs;

    public TriggerIORobot() {
        motor = new TalonFX(ROBOT_TRIGGER_MOTOR_ID);

        triggerMotorInputs = new OnyxMotorInputs(motor, TRIGGER_SUBSYSTEM_NAME, TRIGGER_MOTOR_NAME);

        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }

    @Override
    public void updateInputs(TriggerInputs inputs) {
        triggerMotorInputs.updateInputs();
        inputs.triggerMotorInputs = triggerMotorInputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}
