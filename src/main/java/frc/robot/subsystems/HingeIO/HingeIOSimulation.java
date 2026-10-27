package frc.robot.subsystems.HingeIO;

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
import static frc.robot.subsystems.ConveyanceIO.ConveyanceConstants.FOLLOWER_Motor_Name;
import static frc.robot.subsystems.ConveyanceIO.ConveyanceConstants.MASTER_Motor_Name;
import static frc.robot.subsystems.HingeIO.HingeConstants.HingeSimulationConstants.*;
import static frc.robot.subsystems.HingeIO.HingeConstants.SUBSYSTEM;

public class HingeIOSimulation implements HingeIO {
    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorOutputs hingeMasterMotorOutputs;
    private final OnyxMotorOutputs hingeFollowerMotorOutputs;

    public HingeIOSimulation() {
        motor = new TalonFX(SIMULATION_HINGE_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_HINGE_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_HINGE_LENGTH_METERS, SIMULATION_HINGE_MASS_KG), 1),
                DCMotor.getKrakenX60(SIMULATION_HINGE_NUM_OF_MOTORS));

        hingeMasterMotorOutputs = new OnyxMotorOutputs(motor, SUBSYSTEM, MASTER_Motor_Name, OnyxMotorOutputs.CanBus.RIO);
        hingeFollowerMotorOutputs = new OnyxMotorOutputs(motor, SUBSYSTEM, FOLLOWER_Motor_Name, OnyxMotorOutputs.CanBus.RIO);
        hingeMasterMotorOutputs.updateOutputs();
        hingeFollowerMotorOutputs.updateOutputs();

        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {

        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }


    public void updateOutputs(HingeOutputs HingeOutput) {
        updateMotor(motor, simulatedMotor);

        hingeMasterMotorOutputs.updateOutputs();
        HingeOutput.HingeMotorOutput = hingeMasterMotorOutputs;

        hingeFollowerMotorOutputs.updateOutputs();
        HingeOutput.HingeMotorOutput = hingeFollowerMotorOutputs;

    }


    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }


    public void setNeutralMode(NeutralModeValue neutralMode) {
        motor.setNeutralMode(neutralMode);
    }



    public double getRPS() {
        return hingeMasterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }

    @Override
    public double getHingeAngle() {
        return hingeMasterMotorOutputs.getValue();
    }
}


