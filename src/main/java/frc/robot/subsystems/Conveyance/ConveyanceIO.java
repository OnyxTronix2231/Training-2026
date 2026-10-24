package frc.robot.subsystems.Conveyance;

import frc.robot.lib.OnyxMotorOutputs;

public interface ConveyanceIO {

    void updateOutputs(ConveyanceOutputs outputs);

    class ConveyanceOutputs {
        public OnyxMotorOutputs conveyanceMotorOutputs;
        public OnyxMotorOutputs conveyanceFollowerMotorOutputs;
    }
     double getRPS();

    void setDutyCycle(double dutyCycle);
}
