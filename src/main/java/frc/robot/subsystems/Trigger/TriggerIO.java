package frc.robot.subsystems.Trigger;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface TriggerIO {

    void updateOutputs(TriggerOutputs triggerOutputs);
    class TriggerOutputs {
        public static OnyxMotorOutputs motorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    void setNeutralMode(NeutralModeValue neutralMode);

    double getRPS();

}
