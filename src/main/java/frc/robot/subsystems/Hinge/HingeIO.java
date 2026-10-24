package frc.robot.subsystems.Hinge;


import frc.robot.lib.OnyxMotorOutputs;

public interface HingeIO {

    void updateOutputs(HingeOutputs hingeOutputs);

    class HingeOutputs {
        public static OnyxMotorOutputs motorOutputs;
    }

    double getAngle();

}
