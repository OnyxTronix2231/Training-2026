package frc.robot.subsystems.Conveyance;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface ConveyanceIO {


    void updateOutputs(Conveyanceoutputs Conveyanceoutputs);

    class Conveyanceoutputs {
        public OnyxMotorOutputs masterMotorOutputs;
        public OnyxMotorOutputs followerMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    void setNeutralMode(NeutralModeValue neutralMode);

}

