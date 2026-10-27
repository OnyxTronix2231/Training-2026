package frc.robot.subsystems.Hinge;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface HingeIO {

    void updateOutputs(HingeOutputs hingeOutputs);

    class HingeOutputs {
        public OnyxMotorOutputs motorOutputs;
    }

    void setNeutralMode(NeutralModeValue neutralMode);

    void setDutyCycle(double dutyCycle);

    double getAngle();

}
