package frc.robot.subsystems.lecture.L4.Shooter;

import frc.robot.lib.OnyxMotorOutputs;

public interface ShooterIO {
    void updateInputs(ShooterInputs inputs);

    class ShooterInputs {
        public OnyxMotorOutputs shooterMasterMotorInputs;
    }

    void setDutyCycle(double dutyCycle);

    double getRPS();
}
