package frc.robot.subsystems.answers.L4.Flywheel;

import frc.robot.lib.OnyxMotorInputs;

public interface FlywheelIO {
    void updateInputs(FlywheelInputs inputs);

    class FlywheelInputs {
        OnyxMotorInputs flywheelMasterMotorInputs;
        OnyxMotorInputs flywheelFollowerMotorInputs;
    }

    void setDutyCycle(double dutyCycle);
}
