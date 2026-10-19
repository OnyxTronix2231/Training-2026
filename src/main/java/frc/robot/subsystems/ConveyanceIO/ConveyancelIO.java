package frc.robot.subsystems.ConveyanceIO;

import frc.robot.lib.OnyxMotorOutputs;
import com.ctre.phoenix6.signals.NeutralModeValue;

public interface ConveyancelIO {
    void updateOutputs(ConveyancelOutput Mastermotoroutput,ConveyancelOutput Followeroutput);

    public class ConveyancelOutput{
        public OnyxMotorOutputs Mastermotoroutput;
        public OnyxMotorOutputs Followeroutput;

    }
    void setDutyCycle(double DutyCycle);
    void setNeutralMode(NeutralModeValue neutralMode);
}