package frc.robot.subsystems.Conveyance;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.subsystems.Conveyance.ConveyanceConstants.*;

public class ConveyanceIORobot implements ConveyanceIO{
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final OnyxMotorOutputs masterMotorOutputs;
    private final OnyxMotorOutputs followerMotorOutputs;


    public ConveyanceIORobot(){
        masterMotor = new TalonFX(MASTER_MOTOR_ID);
        followerMotor = new TalonFX(FOLLOWER_MOTOR_ID);
        masterMotorOutputs = new OnyxMotorOutputs(masterMotor,CONVEYANCE_SUBSYSTEM_NAME,CONVEYANCE_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        followerMotorOutputs = new OnyxMotorOutputs(followerMotor,CONVEYANCE_SUBSYSTEM_NAME,CONVEYANCE_FOLLOWER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);

        masterMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());
followerMotor.setControl(new Follower(MASTER_MOTOR_ID, MotorAlignmentValue.Opposed));

    }
public TalonFXConfiguration getTalonFXConfiguration(){
    TalonFXConfiguration talonfx = new TalonFXConfiguration();
    talonfx.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
talonfx.CurrentLimits.SupplyCurrentLimitEnable = true;
talonfx.CurrentLimits.SupplyCurrentLimit = CONVEYANCE_SUPPLY_LIMIT;

    talonfx.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    return talonfx;


}

    @Override
    public void updateOutputs(Conveyanceoutputs Conveyanceoutputs) {
        masterMotorOutputs.updateOutputs();
        Conveyanceoutputs.masterMotorOutputs = masterMotorOutputs;

        followerMotorOutputs.updateOutputs();
        Conveyanceoutputs.followerMotorOutputs = followerMotorOutputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);

    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {
        masterMotor.setNeutralMode(neutralMode);

    }

}
