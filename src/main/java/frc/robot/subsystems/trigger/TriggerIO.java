package frc.robot.subsystems.trigger;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface TriggerIO {

    void updateOutputs(TriggerOutputs inputs);

    class TriggerOutputs {
        public OnyxMotorOutputs triggerMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    void setNeutralMode(NeutralModeValue neutralMode);

    double getRPS();
}