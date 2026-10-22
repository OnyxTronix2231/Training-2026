package frc.robot.subsystems.lecture.L4.Turret;

import frc.robot.lib.OnyxMotorOutputs;

public interface TurretIO {
    void updateOutputs(TurretOutputs outputs);

    class TurretOutputs {
        public OnyxMotorOutputs turretMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    double getAngle();
}
