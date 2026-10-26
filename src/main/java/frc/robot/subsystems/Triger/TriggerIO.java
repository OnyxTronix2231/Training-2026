package frc.robot.subsystems.Triger;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface TriggerIO {
    void updateOutputs(TriggerIO.TrigerOutputs TrigerOutput);

    class TrigerOutputs {
        public OnyxMotorOutputs masterMotorOutput;

    }
    void setDutyCycle(double DutyCycle);
    void setNeutralMode(NeutralModeValue neutralMode);
    double getRPS();
}

