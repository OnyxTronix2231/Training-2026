package frc.robot.subsystems.Elevator;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface ElevatorIO {
    void updateOutputs(ElevatorOutputs elevatorOutputs);

    class ElevatorOutputs {
        public OnyxMotorOutputs elevatorMotorOutputs;
    }

    void setDutyCycle(double dutyCycle);

    void setNeutralMode(NeutralModeValue neutralMode);
}
