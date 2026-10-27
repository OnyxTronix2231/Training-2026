package frc.robot.subsystems.HingeIO;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface HingeIO {
    void updateOutputs(HingeIO.HingeOutputs HingeOutput);

    class HingeOutputs {
        public OnyxMotorOutputs HingeMotorOutput;

    }

    void setDutyCycle(double DutyCycle);

    void setNeutralMode(NeutralModeValue neutralMode);

    double getRPS();
    double getHingeAngle();

}

