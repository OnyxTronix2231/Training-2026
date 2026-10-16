package frc.robot.subsystems.lecture.L6;

import frc.robot.lib.OnyxMotorOutputs;

public interface TriggerIO {
    void updateInputs(TriggerInputs inputs);

    class TriggerInputs {
        public OnyxMotorOutputs triggerMotorInputs;

        // Raw sensor value, filtering happens in Trigger
        public boolean isSensorSeeing;
    }

    void setDutyCycle(double dutyCycle);
}
