package frc.robot.subsystems.Conveyance;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.*;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceRobotBConstants.*;


public class ConveyanceIORobotB implements ConveyanceIO {

    private final TalonFX motor;
    private final OnyxMotorOutputs motorOutputs;
    public ConveyanceIORobotB(){
        motor = new TalonFX(ConveyanceRobotBConstants.CONVEYANCE_MOTOR_ID);
        motorOutputs = new OnyxMotorOutputs(motor, CONVEYANCE_NAME, CONVEYANCE_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        motor.getConfigurator().apply(getMotorConfiguration());
    }
    public TalonFXConfiguration getMotorConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = CONVEYANCE_SUPPLY_LIMIT;

        return configuration;
    }

    @Override
    public void updateOutputs(ConveyanceOutputs conveyanceOutputs) {
        motorOutputs.updateOutputs();
        conveyanceOutputs.masterMotorOutputs.updateOutputs();
        conveyanceOutputs.followerMotorOutputs.updateOutputs();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {
        motor.setNeutralMode(neutralMode);
    }
}
