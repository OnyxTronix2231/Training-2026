package frc.robot.subsystems.Trigger;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface TriggerIO {
    void updateOutputs(HingeOutputs TriggerOutput);

    class HingeOutputs {
        public OnyxMotorOutputs TriggerMotorOutput;

    }

    void setDutyCycle(double DutyCycle);

    void setNeutralMode(NeutralModeValue neutralMode);

    double getRPS();
}

