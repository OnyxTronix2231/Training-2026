package frc.robot.subsystems.answers.L4.Conveyance;

import frc.robot.lib.OnyxMotorOutputs;

public interface ConveyanceIO {
    void updateInputs(ConveynaceInputs inputs);

    class ConveynaceInputs {
        OnyxMotorOutputs conveyanceMasterMotorInputs;
        OnyxMotorOutputs conveyanceFollowerMotorInputs;
    }

    void setDutyCycle(double dutyCycle);
}
