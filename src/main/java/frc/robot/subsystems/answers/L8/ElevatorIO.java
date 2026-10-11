package frc.robot.subsystems.answers.L8;

import frc.robot.lib.OnyxMotorInputs;
import frc.robot.lib.PID.PIDValues;

public interface ElevatorIO {
    void updateInputs(ElevatorInputs inputs);

    class ElevatorInputs {
        public OnyxMotorInputs elevatorMasterMotorInputs;
        public OnyxMotorInputs elevatorFollowerMotorInputs;

        public boolean isSwitchPressed;

        public double encoderPosition;
    }

    void setDutyCycle(double dutyCycle);

    void moveToPosition(double position);

    void updatePID(PIDValues pidValues);

    double getElevatorForwardSoftLimit();
    double getElevatorBackwardSoftLimit();
}
