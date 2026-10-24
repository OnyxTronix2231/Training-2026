package frc.robot.subsystems.Conveyance;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.subsystems.Conveyance.ConveyanceConstants.*;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceRobotAConstants.*;


public class ConveyanceIORobotA implements ConveyanceIO {
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;
    private final OnyxMotorOutputs conveyanceMasterMotorOutputs;
    private final OnyxMotorOutputs conveyanceFollowerMotorOutputs;

    public ConveyanceIORobotA() {
        masterMotor = new TalonFX(CONVEYANCE_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(CONVEYANCE_FOLLOWER_MOTOR_ID);
        conveyanceMasterMotorOutputs = new OnyxMotorOutputs(masterMotor, CONVEYANCE_NAME, CONVEYANCE_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
            conveyanceFollowerMotorOutputs = new OnyxMotorOutputs(followerMotor, CONVEYANCE_NAME, CONVEYANCE_FOLLOWER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        masterMotor.getConfigurator().apply(getMotorConfiguration());
        followerMotor.getConfigurator().apply(getMotorConfiguration());
        followerMotor.setControl(new Follower(CONVEYANCE_MASTER_MOTOR_ID, MotorAlignmentValue.Opposed));
    }

    public TalonFXConfiguration getMotorConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = ConveyanceRobotAConstants.CONVEYANCE_SUPPLY_LIMIT;

        return configuration;
    }

    @Override
    public void updateOutputs(ConveyanceOutputs conveyanceOutputs) {
        conveyanceMasterMotorOutputs.updateOutputs();
        conveyanceFollowerMotorOutputs.updateOutputs();
        conveyanceOutputs.masterMotorOutputs = conveyanceMasterMotorOutputs;
            conveyanceOutputs.followerMotorOutputs = conveyanceFollowerMotorOutputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {
        masterMotor.setNeutralMode(neutralMode);
    }

    @Override
    public double getRPS() {
     return   conveyanceMasterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
