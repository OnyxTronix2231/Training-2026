package frc.robot.subsystems.conveyance;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface ConveyanceIO {
    void updateOutputs(ConveyanceOutputs conveyanceOutputs);

    class ConveyanceOutputs {
        public OnyxMotorOutputs motorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    void setNeutralMode(NeutralModeValue neutralMode);
}
