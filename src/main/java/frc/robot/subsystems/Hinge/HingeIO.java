package frc.robot.subsystems.Hinge;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface HingeIO {
    void updateOutputs(HingeOutputs hingeOutputs);

    class HingeOutputs {
        public OnyxMotorOutputs hingeMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    void setNeutralMode(NeutralModeValue neutralMode);

    double getAngle();
}
