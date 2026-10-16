package frc.robot.subsystems.lecture.L4.Turret;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.OnyxMotorOutputs.CanBus;

import static frc.robot.Constants.updateMotor;
import static frc.robot.subsystems.lecture.L4.Turret.TurretConstants.*;
import static frc.robot.subsystems.lecture.L4.Turret.TurretConstants.TurretSimulationConstants.*;

public class TurretIOSimulation implements TurretIO {
    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorOutputs turretMotorInputs;

    public TurretIOSimulation() {
        motor = new TalonFX(SIMULATION_TURRET_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_TURRET_NUM_OF_MOTORS),
            SingleJointedArmSim.estimateMOI(SIMULATION_TURRET_LENGTH_METERS, SIMULATION_TURRET_MASS_KG), 1),
            DCMotor.getKrakenX60(SIMULATION_TURRET_NUM_OF_MOTORS));

        turretMotorInputs = new OnyxMotorOutputs(motor, TURRET_SUBSYSTEM_NAME, TURRET_MOTOR_NAME, ROTATIONS_TO_DEGREES, CanBus.RIO);
    }

    @Override
    public void updateInputs(TurretInputs inputs) {
        updateMotor(motor, simulatedMotor);

        turretMotorInputs.updateInputs();
        inputs.turretMotorInputs = turretMotorInputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public double getTurretAngle() {
        return turretMotorInputs.getValue();
    }
}
