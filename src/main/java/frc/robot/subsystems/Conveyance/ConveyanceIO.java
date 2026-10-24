package frc.robot.subsystems.Conveyance;

import frc.robot.lib.OnyxMotorOutputs;

public interface ConveyanceIO {

    void updateOutputs(ConveyanceOutputs conveyanceOutputs);

    class ConveyanceOutputs {
        public static OnyxMotorOutputs masterMotorOutputs;
        public static OnyxMotorOutputs followerMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    double getRPS();

}
