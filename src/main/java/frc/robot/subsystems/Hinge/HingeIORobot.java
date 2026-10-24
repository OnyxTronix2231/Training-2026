package frc.robot.subsystems.Hinge;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.subsystems.Conveyance.ConveyanceIO;

import static frc.robot.subsystems.Hinge.HingeConstants.HINGE_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Hinge.HingeConstants.Hinge_RobotAConstants.*;

public class HingeIORobot implements HingeIO{

    private final TalonFX motor;
    private final OnyxMotorOutputs motorOutputs;

    public HingeIORobot(TalonFX motor) {
        this.motor = new TalonFX(HINGE_MOTOR_ID);
        motorOutputs = new OnyxMotorOutputs(motor,HINGE_SUBSYSTEM_NAME ,HINGE_MOTOR_NAME, OnyxMotorOutputs.CanBus.CANIVORE);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        return configuration;
    }


    @Override
    public void updateOutputs(HingeOutputs hingeOutputs) {
        motorOutputs.updateOutputs();
        HingeIO.HingeOutputs.motorOutputs = motorOutputs;
    }

    @Override
    public double getAngle() {
        return motorOutputs.getMotorAngularAccelerationRotPerSecSquared();
    }
}
