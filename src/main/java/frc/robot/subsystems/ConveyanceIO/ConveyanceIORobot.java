package frc.robot.subsystems.ConveyanceIO;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.subsystems.ConveyanceIO.ConveyancelConstans;

import static frc.robot.subsystems.BallIntake.BallIntakeConstants.BallIntakeRobotConstants.ROBOT_BALL_INTAKE_MOTOR_ID;

public class ConveyanceIORobot implements ConveyancelIO {
    private final TalonFX Mastermotor;
    private final TalonFX Followermotor;
    private final OnyxMotorOutputs MastermotorOutputs;
    private final OnyxMotorOutputs FollowermotorOutput;


    public ConveyanceIORobot() {
        Mastermotor = new TalonFX(ConveyancelConstans.Conveyancelport1);
        Followermotor = new TalonFX(ConveyancelConstans.Conveyancelport2);
        MastermotorOutputs = new OnyxMotorOutputs(Mastermotor,ConveyancelConstans.Subsystem,ConveyancelConstans.Conveyancelname1, OnyxMotorOutputs.CanBus.RIO);
        FollowermotorOutput = new OnyxMotorOutputs(Followermotor,ConveyancelConstans.Subsystem,ConveyancelConstans.Conveyancelname2, OnyxMotorOutputs.CanBus.RIO);

        Mastermotor.getConfigurator().apply(getmotorCon());
        Followermotor.setControl(new Follower(ConveyancelConstans.Conveyancelport1, MotorAlignmentValue.Opposed));

    }
    public TalonFXConfiguration getmotorCon(){
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.MotorOutput.Inverted= InvertedValue.CounterClockwise_Positive;
        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = ConveyancelConstans.SUPPLY_LIMIT;
        return configuration;


    }

    @Override
    public void updateOutputs(ConveyancelOutput Mastermotoroutput, ConveyancelOutput Followeroutput) {
        MastermotorOutputs.updateOutputs();
        FollowermotorOutput.updateOutputs();
        

    }

    public void setDutyCycle(double dutyCycle) {
        Mastermotor.set(dutyCycle);


    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {

    }


    public void setNeutrualMode(NeutralModeValue neutrualMode) {

    }
}
