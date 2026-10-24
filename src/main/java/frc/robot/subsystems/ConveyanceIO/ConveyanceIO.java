package frc.robot.subsystems.ConveyanceIO;

import frc.robot.lib.OnyxMotorOutputs;
import com.ctre.phoenix6.signals.NeutralModeValue;

public interface ConveyanceIO {
    void updateOutputs(ConveyanceIO.ConveyanceOutputs conveyanceOutputs);

    class ConveyanceOutputs {
        public OnyxMotorOutputs masterMotorOutput;
        public OnyxMotorOutputs followerOutput;

    }
    void setDutyCycle(double DutyCycle);
    void setNeutralMode(NeutralModeValue neutralMode);
    double getRPS();
}