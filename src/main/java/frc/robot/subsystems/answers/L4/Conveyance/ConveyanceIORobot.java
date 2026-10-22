package frc.robot.subsystems.answers.L4.Conveyance;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.OnyxMotorOutputs.CanBus;

import static frc.robot.subsystems.answers.L4.Conveyance.ConveyanceConstants.*;
import static frc.robot.subsystems.answers.L4.Conveyance.ConveyanceConstants.ConveyanceRobotConstants.*;

public class ConveyanceIORobot implements ConveyanceIO {
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final OnyxMotorOutputs conveyanceMasterMotorOutputs;
    private final OnyxMotorOutputs conveyanceFollowerMotorOutputs;

    public ConveyanceIORobot() {
        masterMotor = new TalonFX(ROBOT_CONVEYANCE_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(ROBOT_CONVEYANCE_FOLLOWER_MOTOR_ID);

        conveyanceMasterMotorOutputs = new OnyxMotorOutputs(masterMotor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_MASTER_MOTOR_NAME, CanBus.RIO);
        conveyanceFollowerMotorOutputs = new OnyxMotorOutputs(followerMotor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_FOLLOWER_MOTOR_NAME, CanBus.RIO);

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
    public void updateOutputs(ConveynaceOutputs outputs) {
        conveyanceMasterMotorOutputs.updateOutputs();
        outputs.conveyanceMasterMotorOutputs = conveyanceMasterMotorOutputs;

        conveyanceFollowerMotorOutputs.updateOutputs();
        outputs.conveyanceFollowerMotorOutputs = conveyanceFollowerMotorOutputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }
}
