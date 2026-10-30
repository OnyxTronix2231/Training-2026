package frc.robot.subsystems.Hinge;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.Hinge.HingeConstants.HINGE_NAME;
import static frc.robot.subsystems.Hinge.HingeConstants.HingeSimulationConstants.*;

public class HingeIOSimulation implements HingeIO {

    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorOutputs motorOutputs;

    public HingeIOSimulation() {
        motor = new TalonFX(HINGE_MOTOR_ID, new CANBus(HINGE_CANBUS));
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_HINGE_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_HINGE_LENGTH_METERS, SIMULATION_HINGE_MASS_KG), SIMULATION_HINGE_GEARING),
                DCMotor.getKrakenX60(SIMULATION_HINGE_NUM_OF_MOTORS));

        motorOutputs = new OnyxMotorOutputs(motor, HINGE_NAME, HINGE_MOTOR_NAME,ROTATIONS_TO_ANGLE, OnyxMotorOutputs.CanBus.CANIVORE);

        motor.getConfigurator().apply(getMotorConfiguration());
    }


    public TalonFXConfiguration getMotorConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = SIMULATION_ANGLE_TO_ROTATIONS(HINGE_FORWARD_SOFT_LIMIT_ANGLE);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = SIMULATION_ANGLE_TO_ROTATIONS(HINGE_REVERSE_SOFT_LIMIT_ANGLE);

        return configuration;
    }

    @Override
    public void updateOutputs(HingeOutputs hingeOutputs) {
        updateMotor(motor, simulatedMotor);

        motorOutputs.updateOutputs();

        hingeOutputs.hingeMotorOutputs = motorOutputs;
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
    public double getAngle() {
        return motorOutputs.getMotorValue().getAsDouble();
    }
}
