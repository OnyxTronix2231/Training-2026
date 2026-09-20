package frc.robot.subsystems.answers.L4.Elevator;

import frc.robot.lib.OnyxMotorInputs;

public interface ElevatorIO {
    void updateInputs(ElevatorInputs inputs);

    class ElevatorInputs {
        public OnyxMotorInputs elevatorMasterMotorInputs;
        public OnyxMotorInputs elevatorFollowerMotorInputs;
    }

    void setDutyCycle(double dutyCycle);
}