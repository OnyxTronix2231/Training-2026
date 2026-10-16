package frc.robot.subsystems.lecture.L6;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.OnyxMotorOutputs.CanBus;

import static frc.robot.subsystems.lecture.L6.TriggerConstants.TRIGGER_MOTOR_NAME;
import static frc.robot.subsystems.lecture.L6.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.lecture.L6.TriggerConstants.TriggerRobotConstants.ROBOT_TRIGGER_MOTOR_ID;
import static frc.robot.subsystems.lecture.L6.TriggerConstants.TriggerRobotConstants.ROBOT_TRIGGER_SENSOR_ID;

public class TriggerIORobot implements TriggerIO {
    private final TalonFX motor;

    private final OnyxMotorOutputs triggerMotorInputs;

    private final DigitalInput sensor;

    public TriggerIORobot() {
        motor = new TalonFX(ROBOT_TRIGGER_MOTOR_ID);

        triggerMotorInputs = new OnyxMotorOutputs(motor, TRIGGER_SUBSYSTEM_NAME, TRIGGER_MOTOR_NAME, CanBus.RIO);

        motor.getConfigurator().apply(getTalonFXConfiguration());

        sensor = new DigitalInput(ROBOT_TRIGGER_SENSOR_ID);
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

        inputs.isSensorSeeing = sensor.get();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}
