package frc.robot.subsystems.answers.L3;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.answers.L3.ConveyanceConstants.*;

public class ConveyanceIORobot implements ConveyanceIO {
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final OnyxMotorInputs conveyanceMasterMotorInputs;
    private final OnyxMotorInputs conveyanceFollowerMotorInputs;

    private final DigitalInput sensor;

    public ConveyanceIORobot() {
        masterMotor = new TalonFX(CONVEYANCE_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(CONVEYANCE_MASTER_MOTOR_ID);

        conveyanceMasterMotorInputs = new OnyxMotorInputs(masterMotor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_MASTER_MOTOR_NAME);
        conveyanceFollowerMotorInputs = new OnyxMotorInputs(followerMotor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_FOLLOWER_MOTOR_NAME);

        masterMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());

        followerMotor.setControl(new Follower(CONVEYANCE_MASTER_MOTOR_ID, MotorAlignmentValue.Opposed));

        sensor = new DigitalInput(CONVEYANCE_SENSOR_ID);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = CONVEYANCE_SUPPLY_LIMIT;

        return configuration;
    }

    @Override
    public void updateInputs(ConveyanceInputs inputs) {
        conveyanceMasterMotorInputs.updateInputs();
        inputs.conveyanceFollowerMotorInputs = conveyanceFollowerMotorInputs;

        conveyanceFollowerMotorInputs.updateInputs();
        inputs.conveyanceFollowerMotorInputs = conveyanceFollowerMotorInputs;

        inputs.isSensorSeeing = sensor.get();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }
}
