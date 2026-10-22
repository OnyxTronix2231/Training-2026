package frc.robot.subsystems.answers.L4.Conveyance;

import frc.robot.lib.OnyxMotorOutputs;

public interface ConveyanceIO {
    void updateOutputs(ConveynaceOutputs outputs);

    class ConveynaceOutputs {
        OnyxMotorOutputs conveyanceMasterMotorOutputs;
        OnyxMotorOutputs conveyanceFollowerMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);
}
