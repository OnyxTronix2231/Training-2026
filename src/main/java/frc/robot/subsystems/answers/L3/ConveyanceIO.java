package frc.robot.subsystems.answers.L3;

import frc.robot.lib.OnyxMotorOutputs;

public interface ConveyanceIO {

    void updateInputs(ConveyanceIO.ConveyanceInputs inputs);

    class ConveyanceInputs {
        public OnyxMotorOutputs conveyanceMasterMotorInputs;
        public OnyxMotorOutputs conveyanceFollowerMotorInputs;
    }

    void setDutyCycle(double dutyCycle);
}
