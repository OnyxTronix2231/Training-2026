package frc.robot.subsystems.answers.L6;

import frc.robot.lib.OnyxMotorOutputs;

public interface TriggerIO {
    void updateInputs(TriggerInputs inputs);

    class TriggerInputs {
        OnyxMotorOutputs triggerMotorInputs;

        boolean isSensorSeeing;
    }

    void setDutyCycle(double dutyCycle);
}
