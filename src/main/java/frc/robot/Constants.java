package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class Constants {
    public static final double PERIODIC_SECONDS = 0.02;

    public static void updateMotor(TalonFX motor, DCMotorSim simulatedMotor) {
        TalonFXSimState motorSimState = motor.getSimState();
        motor.getSimState().setSupplyVoltage(RobotController.getBatteryVoltage());
        simulatedMotor.setInputVoltage(motorSimState.getMotorVoltage());
        simulatedMotor.update(PERIODIC_SECONDS);

        motorSimState.setRawRotorPosition(simulatedMotor.getAngularPositionRotations());
        motorSimState.setRotorVelocity(
            Units.radiansToRotations(simulatedMotor.getAngularVelocityRadPerSec()));
    }
}
