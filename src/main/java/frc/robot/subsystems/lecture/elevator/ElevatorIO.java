package frc.robot.subsystems.lecture.elevator;

import frc.robot.lib.OnyxMotorOutputs;

public interface ElevatorIO {
    void updateInputs(ElevatorInputs inputs);

    public class ElevatorInputs {
        public OnyxMotorOutputs elevatorMasterMotorOutputs;
        public OnyxMotorOutputs elevatorFollowerMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);
}
