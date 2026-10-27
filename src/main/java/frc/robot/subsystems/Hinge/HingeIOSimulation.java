package frc.robot.subsystems.Hinge;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.Hinge.HingeConstants.*;
import static frc.robot.subsystems.Hinge.HingeConstants.HingeSimulationConstants.*;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerSimulationConstants.*;

public class HingeIOSimulation implements HingeIO {
    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;
    private final OnyxMotorOutputs onyxMotorOutputs;

    public HingeIOSimulation() {
        motor = new TalonFX(SIMULATION_TRIGGER_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_HINGE_NUM_OF_MOTOR),
                SingleJointedArmSim.estimateMOI(SIMULATION_HINGE_LENGTH_METERS, SIMULATION_HINGE_MASS_KG), 1),
                DCMotor.getKrakenX60(SIMULATION_HINGE_NUM_OF_MOTOR));
        this.onyxMotorOutputs= new OnyxMotorOutputs(motor, HINGE_SUBSYSTEM_NAME, HINGE_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO);
        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {

        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = Units.degreesToRotations(HINGE_FORWARD_SOFT_LIMIT_DEGREES);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = Units.degreesToRotations(HINGE_REVERSE_SOFT_LIMIT_DEGREES);



        return configuration;
    }

    @Override
    public void updateOutputs(HingeOutputs hingeMotorOutputs) {
        updateMotor(motor,simulatedMotor);

        onyxMotorOutputs.updateOutputs();
        hingeMotorOutputs.hingeMotorOutputs = onyxMotorOutputs;
    }

    @Override
    public double getRPS() {
        return onyxMotorOutputs.getMotorAngularVelocityRotPerSec();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public double getAngle() {
        return onyxMotorOutputs.getValue();
    }
}

