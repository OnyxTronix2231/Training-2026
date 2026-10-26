package frc.robot.subsystems.Trigger;

import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.subsystems.Conveyance.ConveyanceIO;

public interface TriggerIO {
    void updateOutputs(TriggerOutputs outputs);

    class TriggerOutputs {
        public OnyxMotorOutputs TriggerMotorOutputs;
    }
    double getRPS();

    void setDutyCycle(double dutyCycle);
}
