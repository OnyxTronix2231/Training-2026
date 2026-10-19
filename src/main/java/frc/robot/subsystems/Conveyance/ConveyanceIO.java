package frc.robot.subsystems.Conveyance;

import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.subsystems.lecture.elevator.ElevatorIO;

public interface ConveyanceIO {
    void updateInputs(ConveyanceIOOutput inputs);

    public class ConveyanceIOOutput {
        public OnyxMotorOutputs conveyanceMotorOutputs;
    }

    void setDutyCycle(Double dutyCycle);
}
