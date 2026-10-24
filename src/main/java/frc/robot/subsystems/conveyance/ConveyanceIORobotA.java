package frc.robot.subsystems.conveyance;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.subsystems.conveyance.ConveyanceConstants.*;
import static frc.robot.subsystems.conveyance.ConveyanceConstants.ConveyanceRobotAConstants.*;

public class ConveyanceIORobotA implements ConveyanceIO {
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;
    private final OnyxMotorOutputs conveyanceMasterMotorOutputs;
    private final OnyxMotorOutputs conveyanceFollowerMotorOutputs;

    public ConveyanceIORobotA() {
        masterMotor = new TalonFX(ROBOT_A_MASTER_CONVEYANCE_MOTOR_ID);
        followerMotor = new TalonFX(ROBOT_A_FOLLOWER_CONVEYANCE_MOTOR_ID);
        conveyanceMasterMotorOutputs = new OnyxMotorOutputs(masterMotor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        conveyanceFollowerMotorOutputs = new OnyxMotorOutputs(followerMotor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_FOLLOWER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        masterMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());

        followerMotor.setControl(new Follower(ROBOT_A_MASTER_CONVEYANCE_MOTOR_ID, MotorAlignmentValue.Opposed));

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
    public void updateOutputs(ConveyanceOutputs inputs) {
        conveyanceMasterMotorOutputs.updateOutputs();
        conveyanceFollowerMotorOutputs.updateOutputs();
        inputs.conveyanceMasterMotorOutputs = conveyanceMasterMotorOutputs;
        inputs.conveyanceFollowerMotorOutputs = conveyanceFollowerMotorOutputs;
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
        return conveyanceMasterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
