package frc.robot.subsystems.Conveyance;

import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.subsystems.Conveyance.ConveyanceConstants.CONVEYANCE_MOTOR_NAME;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.CONVEYANCE_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceRobotConstants.R0B0T_Conveyance_DUTY_CYCLE;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceRobotConstants.ROBOT_Conveyance_MOTOR_ID;

public class ConveyanceIORobot implements ConveyanceIO {
    private final TalonFX motor;
    private final OnyxMotorOutputs conveyanceMotorOutputs;

    public ConveyanceIORobot() {
        this.motor=new TalonFX(ROBOT_Conveyance_MOTOR_ID);
        this.conveyanceMotorOutputs= new OnyxMotorOutputs(motor,CONVEYANCE_SUBSYSTEM_NAME,CONVEYANCE_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
    }

    @Override
    public void updateInputs(ConveyanceIOOutput inputs) {

    }

    @Override
    public void setDutyCycle(Double dutyCycle) {

    }
}
