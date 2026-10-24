package frc.robot.subsystems.Conveyance;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface ConveyanceIO {

    void updateOutputs(ConveyanceOutputs conveyanceOutputs);

    class ConveyanceOutputs {
        public OnyxMotorOutputs masterMotorOutputs;
        public OnyxMotorOutputs followerMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    double getRPS();

    void setNeutralMode(NeutralModeValue neutralMode);

}
