package frc.robot.subsystems.Trigger;

import frc.robot.lib.OnyxMotorOutputs;

public interface TriggerIO {
    void updateOutputs(TriggerOutputs outputs);

    class TriggerOutputs {
        public OnyxMotorOutputs triggerMotorOutputs;
    }
    double getRPS();

    void setDutyCycle(double dutyCycle);
}
