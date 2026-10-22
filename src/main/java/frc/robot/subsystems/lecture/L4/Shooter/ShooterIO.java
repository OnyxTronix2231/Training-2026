package frc.robot.subsystems.lecture.L4.Shooter;

import frc.robot.lib.OnyxMotorOutputs;

public interface ShooterIO {
    void updateOutputs(ShooterOutputs outputs);

    class ShooterOutputs {
        public OnyxMotorOutputs shooterMasterMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    double getRPS();
}
