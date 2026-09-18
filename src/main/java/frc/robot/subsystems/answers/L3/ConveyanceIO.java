package frc.robot.subsystems.answers.L3;

import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorInputs;
import frc.robot.lib.PID.PIDValues;

public interface ConveyanceIO {

    void updateInputs(ConveyanceIO.ConveyanceInputs inputs);

    class ConveyanceInputs {
        public OnyxMotorInputs conveyanceMasterMotorInputs;
        public OnyxMotorInputs conveyanceFollowerMotorInputs;

        public boolean isSensorSeeing;
    }

    void setDutyCycle(double dutyCycle);
}
