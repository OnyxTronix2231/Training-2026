package frc.robot.subsystems.answers.L5;

import frc.robot.lib.OnyxMotorInputs;

public interface TriggerIO {
    void updateInputs(TriggerInputs inputs);

    class TriggerInputs {
        OnyxMotorInputs triggerMotorInputs;
    }

    void setDutyCycle(double dutyCycle);
}
