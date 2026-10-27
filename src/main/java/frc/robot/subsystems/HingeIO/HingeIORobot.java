package frc.robot.subsystems.HingeIO;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.util.Units;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.subsystems.Trigger.TriggerConstants;

import static frc.robot.subsystems.HingeIO.HingeConstants.ROTATION_TO_DEGREES;

public class HingeIORobot implements HingeIO {
    private final TalonFX Mastermotor;
    private final TalonFX Followermotor;
    private final OnyxMotorOutputs MastermotorOutputs;
    private final OnyxMotorOutputs FollowermotorOutput;


    public HingeIORobot() {
        Mastermotor = new TalonFX(HingeConstants.RobotIOHingeConstants.HINGE_MASTER_MOTOR_ID);
        Followermotor = new TalonFX(HingeConstants.RobotIOHingeConstants.HINGE_FOLLOWER_MOTOR_ID);
        MastermotorOutputs = new OnyxMotorOutputs(Mastermotor, HingeConstants.SUBSYSTEM, HingeConstants.HINGE_MOTOR_NAME,ROTATION_TO_DEGREES, OnyxMotorOutputs.CanBus.RIO);
        FollowermotorOutput = new OnyxMotorOutputs(Followermotor, HingeConstants.SUBSYSTEM, HingeConstants.HINGE_MOTOR_NAME,ROTATION_TO_DEGREES, OnyxMotorOutputs.CanBus.RIO);

        Mastermotor.getConfigurator().apply(getmotorCon());
        Followermotor.setControl(new Follower(HingeConstants.RobotIOHingeConstants.HINGE_MASTER_MOTOR_ID, MotorAlignmentValue.Opposed));

    }

    public TalonFXConfiguration getmotorCon() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = HingeConstants.SUPPLY_LIMIT;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = Units.degreesToRotations(180);
        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold= Units.degreesToRotations(0);
        return configuration;


    }


    public void updateOutputs(HingeIO.HingeOutputs hingeOutputs) {
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

    @Override
    public double getHingeAngle() {
        return getHingeAngle();
    }


}
