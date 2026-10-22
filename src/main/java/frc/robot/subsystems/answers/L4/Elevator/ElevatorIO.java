package frc.robot.subsystems.answers.L4.Elevator;

import frc.robot.lib.OnyxMotorOutputs;

public interface ElevatorIO {
    void updateOutputs(ElevatorOutputs outputs);

    class ElevatorOutputs {
        public OnyxMotorOutputs elevatorMasterMotorOutputs;
        public OnyxMotorOutputs elevatorFollowerMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    double getLength();
}