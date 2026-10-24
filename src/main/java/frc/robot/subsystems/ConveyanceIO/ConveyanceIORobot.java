package frc.robot.subsystems.ConveyanceIO;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public class ConveyanceIORobot implements ConveyanceIO {
    private final TalonFX Mastermotor;
    private final TalonFX Followermotor;
    private final OnyxMotorOutputs MastermotorOutputs;
    private final OnyxMotorOutputs FollowermotorOutput;


    public ConveyanceIORobot() {
        Mastermotor = new TalonFX(ConveyanceConstants.CONVEYANCE_MASTER_MOTOR_ID);
        Followermotor = new TalonFX(ConveyanceConstants.CONVEYANCE_FOLLOWER_MOTOR_ID);
        MastermotorOutputs = new OnyxMotorOutputs(Mastermotor, ConveyanceConstants.SUBSYSTEM, ConveyanceConstants.FOLLOWER_Motor_Name, OnyxMotorOutputs.CanBus.RIO);
        FollowermotorOutput = new OnyxMotorOutputs(Followermotor, ConveyanceConstants.SUBSYSTEM, ConveyanceConstants.MASTER_Motor_Name, OnyxMotorOutputs.CanBus.RIO);

        Mastermotor.getConfigurator().apply(getmotorCon());
        Followermotor.setControl(new Follower(ConveyanceConstants.CONVEYANCE_MASTER_MOTOR_ID, MotorAlignmentValue.Opposed));

    }
    public TalonFXConfiguration getmotorCon(){
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.MotorOutput.Inverted= InvertedValue.CounterClockwise_Positive;
        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = ConveyanceConstants.SUPPLY_LIMIT;
        return configuration;


    }

    @Override
    public void updateOutputs(ConveyanceIO.ConveyanceOutputs conveyanceOutputs) {
        MastermotorOutputs.updateOutputs();
        FollowermotorOutput.updateOutputs();
        

    }

    public void setDutyCycle(double dutyCycle) {
        Mastermotor.set(dutyCycle);


    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {

    }

    @Override
    public double getRPS() {
        return 0;
    }


    public void setNeutrualMode(NeutralModeValue neutrualMode) {

    }
}
