package frc.robot.subsystems.lecture.wierdElevator;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

public interface WierdElevatorIO {

    void updateOutputs(WierdElevatorOutputs elevatorOutputs);

    class WierdElevatorOutputs {
        public OnyxMotorOutputs masterMotorOutputs;
        public OnyxMotorOutputs followerMotorOutputs;
    }

    void moveToHeight(double height);

    void setDutyCycle(double dutyCycle);

    double getHeight();

    void moveToStartingPosition();

    void setNeutralMode(NeutralModeValue neutralMode);
}

