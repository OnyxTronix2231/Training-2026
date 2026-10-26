package frc.robot.subsystems.conveyance;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface ConveyanceIO {

    void updateOutputs(ConveyanceOutputs inputs);

    class ConveyanceOutputs {
        public OnyxMotorOutputs conveyanceMasterMotorOutputs;
        public OnyxMotorOutputs conveyanceFollowerMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    void setNeutralMode(NeutralModeValue neutralMode);

    double getRPS();
}