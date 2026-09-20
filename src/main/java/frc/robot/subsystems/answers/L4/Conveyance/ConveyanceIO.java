package frc.robot.subsystems.answers.L4.Conveyance;

import frc.robot.lib.OnyxMotorInputs;

public interface ConveyanceIO {
    void updateInputs(ConveynaceInputs inputs);

    class ConveynaceInputs {
        OnyxMotorInputs conveyanceMasterMotorInputs;
        OnyxMotorInputs conveyanceFollowerMotorInputs;

        boolean isSensorSeeing;
    }

    void setDutyCycle(double dutyCycle);
}
