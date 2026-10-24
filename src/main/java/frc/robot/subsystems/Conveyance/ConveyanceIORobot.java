package frc.robot.subsystems.Conveyance;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.subsystems.Conveyance.ConveyanceConstants.*;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceRobotConstants.*;

public class ConveyanceIORobot implements ConveyanceIO {

    private final TalonFX motor;
    private final TalonFX followerMotor;
    private final OnyxMotorOutputs conveyanceFollowerMotorOutputs;
    private final OnyxMotorOutputs conveyanceMotorOutputs;


    public ConveyanceIORobot() {
        followerMotor=new TalonFX(ROBOT_CONVEYANCE_FOLLOWER_MOTOR_ID);
        this.conveyanceFollowerMotorOutputs = new OnyxMotorOutputs(followerMotor,CONVEYANCE_SUBSYSTEM_NAME,CONVEYANCE_FOLLOWER_MOTOR_NAME,OnyxMotorOutputs.CanBus.RIO);
        motor = new TalonFX(ROBOT_CONVEYANCE_MOTOR_ID);
        this.conveyanceMotorOutputs = new OnyxMotorOutputs(motor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        motor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.setControl(new Follower(ROBOT_CONVEYANCE_MOTOR_ID, MotorAlignmentValue.Opposed));
    }

    public TalonFXConfiguration getTalonFXConfiguration() {

        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.CurrentLimits.SupplyCurrentLimitEnable=true;

        configuration.CurrentLimits.SupplyCurrentLimit = ROBOT_Conveyance_SUPPLY_LIMIT;


        return configuration;

    }

    @Override
    public void updateOutputs(ConveyanceOutputs outputs) {
        conveyanceMotorOutputs.updateOutputs();
        outputs.conveyanceMotorOutputs=conveyanceMotorOutputs;

        conveyanceFollowerMotorOutputs.updateOutputs();
        outputs.conveyanceFollowerMotorOutputs.updateOutputs();
    }

    @Override
    public double getRPS() {
        return conveyanceMotorOutputs.getMotorAngularVelocityRotPerSec();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}
