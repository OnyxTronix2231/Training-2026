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

import frc.robot.lib.OnyxMotorOutputs.CanBus;

public class ConveyanceIORobot implements ConveyanceIO {

    private final TalonFX masterMotor;
    private final OnyxMotorOutputs masterMotorOutputs;

    private final TalonFX followerMotor;
    private final OnyxMotorOutputs followerMotorOutputs;

    public ConveyanceIORobot(OnyxMotorOutputs motorOutputs) {
        masterMotor = new TalonFX(CONVEYANCE_MASTER_MOTOR_ID);
        masterMotorOutputs = new OnyxMotorOutputs(masterMotor,CONVEYANCE_SUBSYSTEM_NAME ,CONVEYANCE_MASTER_MOTOR_NAME, CanBus.RIO);

        followerMotor = new TalonFX(CONVEYANCE_FOLLOWER_MOTOR_ID);
        followerMotorOutputs = new OnyxMotorOutputs(followerMotor,CONVEYANCE_SUBSYSTEM_NAME ,CONVEYANCE_FOLLOWER_MOTOR_NAME, CanBus.RIO);

        followerMotor.setControl(new Follower(CONVEYANCE_MASTER_MOTOR_ID, MotorAlignmentValue.Opposed));
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = CONVEYANCE_SUPPLY_LIMIT;

        return configuration;
    }

    @Override
    public void updateOutputs(ConveyanceIO.ConveyanceOutputs shooterOutputs) {
        masterMotorOutputs.updateOutputs();
        ConveyanceOutputs.masterMotorOutputs = masterMotorOutputs;

        followerMotorOutputs.updateOutputs();
        ConveyanceOutputs.followerMotorOutputs = followerMotorOutputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {};

    @Override
    public double getRPS() {
        return masterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
