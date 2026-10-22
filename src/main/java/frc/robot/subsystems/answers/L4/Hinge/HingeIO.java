package frc.robot.subsystems.answers.L4.Hinge;

import frc.robot.lib.OnyxMotorOutputs;

public interface HingeIO {
    void updateOutputs(HingeOutputs outputs);

    class HingeOutputs {
        OnyxMotorOutputs hingeMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);
}
