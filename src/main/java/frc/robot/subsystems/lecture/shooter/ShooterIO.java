package frc.robot.subsystems.lecture.shooter;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface ShooterIO {

    void updateOutputs(ShooterOutputs shooterOutputs);

    class ShooterOutputs {
        public OnyxMotorOutputs masterMotorOutputs;
        public OnyxMotorOutputs followerMotorOutputs;

        public boolean isSensorSeeing;
    }

    void setDutyCycle(double dutyCycle);

    void setNeutralMode(NeutralModeValue neutralMode);

    double getRPS();

}

