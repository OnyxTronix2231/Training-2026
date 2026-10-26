package frc.robot.subsystems.Trigger;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface TriggerIO {

    void updateOutputs(TriggerIO.TriggerOutputs triggerOutputs);

    class TriggerOutputs {
        public OnyxMotorOutputs triggerMasterMotorOutputs;
        public OnyxMotorOutputs triggerFollowerMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    void setNeutralMode(NeutralModeValue neutralMode);

    double getRPS();
}
