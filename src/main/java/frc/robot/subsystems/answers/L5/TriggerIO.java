package frc.robot.subsystems.answers.L5;

import frc.robot.lib.OnyxMotorOutputs;

public interface TriggerIO {
    void updateInputs(TriggerInputs inputs);

    class TriggerInputs {
        OnyxMotorOutputs triggerMotorInputs;
    }

    void setDutyCycle(double dutyCycle);
}
