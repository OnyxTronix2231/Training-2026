package frc.robot.subsystems.answers.L3;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.answers.L3.ConveyanceConstants.*;
import static frc.robot.subsystems.answers.L3.ConveyanceConstants.ConveynaceConstantsRobot.*;

public class ConveyanceIORobot implements ConveyanceIO {
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final OnyxMotorInputs conveyanceMasterMotorInputs;
    private final OnyxMotorInputs conveyanceFollowerMotorInputs;

    public ConveyanceIORobot() {
        masterMotor = new TalonFX(ROBOT_CONVEYANCE_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(ROBOT_CONVEYANCE_FOLLOWER_MOTOR_ID);

        conveyanceMasterMotorInputs = new OnyxMotorInputs(masterMotor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_MASTER_MOTOR_NAME);
        conveyanceFollowerMotorInputs = new OnyxMotorInputs(followerMotor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_FOLLOWER_MOTOR_NAME);

        masterMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());

        followerMotor.setControl(new Follower(ROBOT_CONVEYANCE_MASTER_MOTOR_ID, MotorAlignmentValue.Opposed));
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = ROBOT_CONVEYANCE_SUPPLY_LIMIT;

        return configuration;
    }

    @Override
    public void updateInputs(ConveyanceInputs inputs) {
        conveyanceMasterMotorInputs.updateInputs();
        inputs.conveyanceFollowerMotorInputs = conveyanceFollowerMotorInputs;

        conveyanceFollowerMotorInputs.updateInputs();
        inputs.conveyanceFollowerMotorInputs = conveyanceFollowerMotorInputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }
}
