package frc.robot.subsystems.answers.L4.Elevator;

import frc.robot.lib.OnyxMotorOutputs;

public interface ElevatorIO {
    void updateInputs(ElevatorInputs inputs);

    class ElevatorInputs {
        public OnyxMotorOutputs elevatorMasterMotorInputs;
        public OnyxMotorOutputs elevatorFollowerMotorInputs;
    }

    void setDutyCycle(double dutyCycle);
}