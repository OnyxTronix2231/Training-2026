package frc.robot.subsystems.answers.L4.Hinge;

import frc.robot.lib.OnyxMotorOutputs;

public interface HingeIO {
    void updateInputs(HingeInputs inputs);

    class HingeInputs {
        OnyxMotorOutputs hingeMotorInputs;
    }

    void setDutyCycle(double dutyCycle);
}
