package frc.robot.subsystems.Hinge;

import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.subsystems.Trigger.TriggerIO;

public interface HingeIO {
    void updateOutputs(HingeOutputs hingeMotorOutputs);

    class HingeOutputs {
        public OnyxMotorOutputs hingeMotorOutputs;
    }
    double getRPS();

    void setDutyCycle(double dutyCycle);
    double getAngle();
}