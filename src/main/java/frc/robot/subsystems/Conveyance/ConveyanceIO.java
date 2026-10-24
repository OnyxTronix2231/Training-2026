package frc.robot.subsystems.Conveyance;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.subsystems.lecture.shooter.ShooterIO;

public interface ConveyanceIO {

    void updateOutputs(ConveyanceIO.ConveyanceOutputs conveyanceOutputs);

    class ConveyanceOutputs {
        public OnyxMotorOutputs masterMotorOutputs;
        public OnyxMotorOutputs followerMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    void setNeutralMode(NeutralModeValue neutralMode);

    double getRPS();
}
