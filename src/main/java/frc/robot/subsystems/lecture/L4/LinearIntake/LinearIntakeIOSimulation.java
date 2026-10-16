package frc.robot.subsystems.lecture.L4.LinearIntake;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.OnyxMotorOutputs.CanBus;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.lecture.L4.LinearIntake.LinearIntakeConstants.*;
import static frc.robot.subsystems.lecture.L4.LinearIntake.LinearIntakeConstants.LinearIntakeSimulationConstants.*;

public class LinearIntakeIOSimulation implements LinearIntakeIO {
    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorOutputs linearIntakeMotorInputs;

    public LinearIntakeIOSimulation() {
        motor = new TalonFX(SIMULATION_LINEAR_INTAKE_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_LINEAR_INTAKE_NUM_OF_MOTORS),
            SingleJointedArmSim.estimateMOI(SIMULATION_LINEAR_INTAKE_LENGTH_METERS, SIMULATION_LINEAR_INTAKE_MASS_KG), 1),
            DCMotor.getKrakenX60(SIMULATION_LINEAR_INTAKE_NUM_OF_MOTORS));

        linearIntakeMotorInputs = new OnyxMotorOutputs(motor, LINEAR_INTAKE_SUBSYSTEM_NAME, LINEAR_INTAKE_MOTOR_NAME, ROTATIONS_TO_METERS, CanBus.RIO);
    }

    @Override
    public void updateInputs(LinearIntakeInputs inputs) {
        updateMotor(motor, simulatedMotor);

        linearIntakeMotorInputs.updateInputs();
        inputs.linearIntakeMotorInputs = linearIntakeMotorInputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public double getPosition() {
        return linearIntakeMotorInputs.getValue();
    }
}
