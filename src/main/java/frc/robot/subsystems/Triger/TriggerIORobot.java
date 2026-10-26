package frc.robot.subsystems.Triger;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.subsystems.ConveyanceIO.ConveyanceConstants;
import frc.robot.subsystems.ConveyanceIO.ConveyanceIO;

public class TriggerIORobot {
    private final TalonFX Mastermotor;
    private final TalonFX Followermotor;
    private final OnyxMotorOutputs MastermotorOutputs;
    private final OnyxMotorOutputs FollowermotorOutput;


    public TriggerIORobot() {
        Mastermotor = new TalonFX(TriggerConstants.TRIGGER_MASTER_MOTOR_ID);
        Followermotor = new TalonFX(TriggerConstants.TRIGGER_FOLLOWER_MOTOR_ID);
        MastermotorOutputs = new OnyxMotorOutputs(Mastermotor, TriggerConstants.SUBSYSTEM, TriggerConstants.TRIGGER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        FollowermotorOutput = new OnyxMotorOutputs(Followermotor, TriggerConstants.SUBSYSTEM, TriggerConstants.TRIGGER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);

        Mastermotor.getConfigurator().apply(getmotorCon());
        Followermotor.setControl(new Follower(TriggerConstants.TRIGGER_MASTER_MOTOR_ID, MotorAlignmentValue.Opposed));

    }
    public TalonFXConfiguration getmotorCon(){
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.MotorOutput.Inverted= InvertedValue.CounterClockwise_Positive;
        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = TriggerConstants.SUPPLY_LIMIT;
        return configuration;


    }


    public void updateOutputs(TriggerIO.TrigerOutputs triggerOutputs) {
        MastermotorOutputs.updateOutputs();
        FollowermotorOutput.updateOutputs();


    }

    public void setDutyCycle(double dutyCycle) {
        Mastermotor.set(dutyCycle);


    }

    public void setNeutralMode(NeutralModeValue neutralMode) {

    }


    public double getRPS() {
        return 0;
    }


}


