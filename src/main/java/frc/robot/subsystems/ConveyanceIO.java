package frc.robot.subsystems;

import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.subsystems.lecture.elevator.ElevatorIO;

public interface ConveyanceIO {

    void updateInputs(ConveyanceIO.ConveyanceInputs inputs);

    public class ConveyanceInputs {
        public OnyxMotorOutputs conveyanceMasterMotorOutputs;
        public OnyxMotorOutputs conveyanceFollowerMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);
}
