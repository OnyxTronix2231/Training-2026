package frc.robot.subsystems.Hinge;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.subsystems.conveyance.ConveyanceIO;

public interface HingeIO {

    void updateOutputs(HingeIO.HingeOutputs hingeOutputs);

    class HingeOutputs {
        public OnyxMotorOutputs motorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    void setNeutralMode(NeutralModeValue neutralMode);

    double getAngle();
}
