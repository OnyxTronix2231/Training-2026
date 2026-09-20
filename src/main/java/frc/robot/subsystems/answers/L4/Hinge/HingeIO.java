package frc.robot.subsystems.answers.L4.Hinge;

import frc.robot.lib.OnyxMotorInputs;

public interface HingeIO {
    void updateInputs(HingeInputs inputs);

    class HingeInputs {
        OnyxMotorInputs hingeMotorInputs;
    }

    void setDutyCycle(double dutyCycle);
}
