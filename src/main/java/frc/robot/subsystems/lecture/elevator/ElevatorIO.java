package frc.robot.subsystems.lecture.elevator;

import frc.robot.lib.OnyxMotorOutputs;

public interface ElevatorIO {
    void updateInputs(ElevatorOutputs inputs);

    class ElevatorOutputs {
        public OnyxMotorOutputs elevatorMasterMotorOutputs;
        public OnyxMotorOutputs elevatorFollowerMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);
}
