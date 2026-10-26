package frc.robot.subsystems.Conveyance;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface ConveyanceIO {

    void updateOutputs(ConveyanceOutputs conveyanceOutputs);

    class ConveyanceOutputs {
        public static OnyxMotorOutputs masterMotorOutputs;
        public static OnyxMotorOutputs followerMotorOutputs;
    }

    void setNeutralMode(NeutralModeValue neutralMode);

    void setDutyCycle(double dutyCycle);

    double getRPS();

}
