package frc.robot.subsystems.lecture.L4.LinearIntake;

import frc.robot.lib.OnyxMotorOutputs;

public interface LinearIntakeIO {
    void updateOutputs(LinearIntakeOutputs outputs);

    class LinearIntakeOutputs {
        public OnyxMotorOutputs linearIntakeMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    double getPosition();
}
