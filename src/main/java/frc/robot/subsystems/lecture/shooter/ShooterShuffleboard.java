package frc.robot.subsystems.lecture.shooter;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.lib.PhysicalTelemetryEntries;
import frc.robot.logging.motorSimulation.SimulatedSensors;

import static frc.robot.subsystems.lecture.shooter.ShooterConstants.SHOOTER_DEFAULT_VELOCITY;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.SHOOTER_SUBSYSTEM_NAME;

public class ShooterShuffleboard {

    public ShooterShuffleboard(ShooterIO.ShooterOutputs shooterOutputs) {
        ShuffleboardTab tab = Shuffleboard.getTab(SHOOTER_SUBSYSTEM_NAME);

        new PhysicalTelemetryEntries(SHOOTER_SUBSYSTEM_NAME, SHOOTER_SUBSYSTEM_NAME,
            () -> shooterOutputs.masterMotorOutputs.getMotorAngularVelocityRotPerSec(),
            () -> shooterOutputs.masterMotorOutputs.getMotorAngularAccelerationRotPerSecSquared(),
            SHOOTER_DEFAULT_VELOCITY
        );

        // Flips the simulated sensor, which is what ShooterIOSimulation reports as isSensorSeeing
        tab.add("toggle shooter sensor", new InstantCommand(() ->
            SimulatedSensors.setSimulatedShooterSensorSeeing(!SimulatedSensors.isSimulatedShooterSensorSeeing())));

        tab.addBoolean("simulated sensor", SimulatedSensors::isSimulatedShooterSensorSeeing);
        tab.addBoolean("sensor seeing", () -> shooterOutputs.isSensorSeeing);
    }
}
