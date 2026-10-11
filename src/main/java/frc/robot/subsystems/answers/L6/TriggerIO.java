package frc.robot.subsystems.answers.L6;

import frc.robot.lib.OnyxMotorInputs;

public interface TriggerIO {
    void updateInputs(TriggerInputs inputs);

    class TriggerInputs {
        OnyxMotorInputs triggerMotorInputs;

        boolean isSensorSeeing;
    }

    void setDutyCycle(double dutyCycle);
}
