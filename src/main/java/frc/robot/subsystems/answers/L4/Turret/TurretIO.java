package frc.robot.subsystems.answers.L4.Turret;

import frc.robot.lib.OnyxMotorInputs;

public interface TurretIO {
    void updateInputs(TurretInputs inputs);

    class TurretInputs {
        public OnyxMotorInputs turretMotorInputs;

        public double encoderPosition;
    }

    void setDutyCycle(double dutyCycle);
}
