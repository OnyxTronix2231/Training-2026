package frc.robot.subsystems.lecture.L4.LinearIntake;

import frc.robot.lib.OnyxMotorOutputs;

public interface LinearIntakeIO {
    void updateInputs(LinearIntakeInputs inputs);

    class LinearIntakeInputs {
        public OnyxMotorOutputs linearIntakeMotorInputs;
    }

    void setDutyCycle(double dutyCycle);

    double getPosition();
}
