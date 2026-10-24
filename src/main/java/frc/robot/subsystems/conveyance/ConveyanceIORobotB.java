package frc.robot.subsystems.conveyance;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.subsystems.conveyance.ConveyanceConstants.*;
import static frc.robot.subsystems.conveyance.ConveyanceConstants.ConveyanceRobotBConstants.*;

public class ConveyanceIORobotB implements ConveyanceIO {
    private final TalonFX motor;
    private final OnyxMotorOutputs conveyanceMotorOutputs;

    public ConveyanceIORobotB() {
        this.motor = new TalonFX(ROBOT_B_Conveyance_MOTOR_ID);
        conveyanceMotorOutputs = new OnyxMotorOutputs(motor, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = CONVEYANCE_SUPPLY_LIMIT;
        return configuration;
    }

    @Override
    public void updateOutputs(ConveyanceOutputs inputs) {
        conveyanceMotorOutputs.updateOutputs();
        inputs.conveyanceMasterMotorOutputs = conveyanceMotorOutputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {
        motor.setNeutralMode(neutralMode);
    }

    @Override
    public double getRPS() {
        return conveyanceMotorOutputs.getMotorAngularVelocityRotPerSec();
    }
}
