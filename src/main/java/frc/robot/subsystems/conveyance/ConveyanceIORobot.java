package frc.robot.subsystems.conveyance;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.subsystems.conveyance.ConveyanceConstants.*;
import static frc.robot.subsystems.conveyance.ConveyanceConstants.ConveyanceRobotConstants.*;

public class ConveyanceIORobot implements ConveyanceIO {
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final OnyxMotorOutputs masterMotorOutputs;
    private final OnyxMotorOutputs followerMotorOutputs;

    public ConveyanceIORobot() {
        masterMotor = new TalonFX(CONVEYANCE_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(CONVEYANCE_FOLLOWER_MOTOR_ID);

        masterMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());

        followerMotor.setControl(new Follower(CONVEYANCE_MASTER_MOTOR_ID, MotorAlignmentValue.Opposed));

        masterMotorOutputs = new OnyxMotorOutputs(masterMotor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        followerMotorOutputs = new OnyxMotorOutputs(followerMotor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_FOLLOWER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = HOOD_SUPPLY_LIMIT;

        return configuration;
    }

    @Override
    public void updateOutputs(ConveyanceOutputs conveyanceOutputs) {
        masterMotorOutputs.updateOutputs();
        conveyanceOutputs.masterMotorOutputs = masterMotorOutputs;

        followerMotorOutputs.updateOutputs();
        conveyanceOutputs.followerMotorOutputs = followerMotorOutputs;
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
    public double getRPS(){
        return masterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
