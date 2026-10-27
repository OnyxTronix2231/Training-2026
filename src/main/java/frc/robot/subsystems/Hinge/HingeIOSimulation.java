package frc.robot.subsystems.Hinge;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorOutputs;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.SIMULATION_CONVEYANCE_NUM_OF_MOTORS;
import static frc.robot.subsystems.Hinge.HingeConstants.HINGE_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Hinge.HingeConstants.Hinge_RobotAConstants.HINGE_MOTOR_ID;
import static frc.robot.subsystems.Hinge.HingeConstants.Hinge_RobotAConstants.HINGE_MOTOR_NAME;
import static frc.robot.subsystems.Hinge.HingeConstants.Hinge_Simulation_Constants.ROTATIONS_TO_DEGREES;

public class HingeIOSimulation implements HingeIO{
    private final DCMotorSim simulatorMotor;
    private final TalonFX motor;
    private final OnyxMotorOutputs motorOutputs;

    public HingeIOSimulation() {
        this.motor = new TalonFX(HINGE_MOTOR_ID);
        simulatorMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(0.001,0.001), 1),
                DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTORS));
        motorOutputs = new OnyxMotorOutputs(motor,HINGE_SUBSYSTEM_NAME ,HINGE_MOTOR_NAME,ROTATIONS_TO_DEGREES,OnyxMotorOutputs.CanBus.CANIVORE);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }


    @Override
    public void updateOutputs(HingeIO.HingeOutputs hingeOutputs) {
        updateMotor(motor, simulatorMotor);
        motorOutputs.updateOutputs();
        hingeOutputs.motorOutputs = motorOutputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {};

    @Override
    public double getAngle() {
        return motorOutputs.getMotorAngularAccelerationRotPerSecSquared();
    }
}
