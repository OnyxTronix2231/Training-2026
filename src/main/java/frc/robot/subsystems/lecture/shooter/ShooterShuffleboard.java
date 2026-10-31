package frc.robot.subsystems.lecture.shooter;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.lib.PhysicalTelemetryEntries;
import frc.robot.logging.motorSimulation.SimulatedSensors;

import static frc.robot.subsystems.lecture.shooter.ShooterConstants.SHOOTER_DEFAULT_VELOCITY;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.SHOOTER_SUBSYSTEM_NAME;

public class ShooterShuffleboard {

    public ShooterShuffleboard() {
        ShuffleboardTab tab = Shuffleboard.getTab(SHOOTER_SUBSYSTEM_NAME);
        Shooter shooter = Shooter.getInstance();

        new PhysicalTelemetryEntries(SHOOTER_SUBSYSTEM_NAME, SHOOTER_SUBSYSTEM_NAME,
            () -> shooter.getRPS(),
            () -> shooter.getAcceleration(),
            SHOOTER_DEFAULT_VELOCITY
        );

        // Both values, so the debounce time can be calibrated against the log instead of guessed
        tab.addBoolean("Sensor Raw", () -> shooter.isSensorSeeingRaw());
        tab.addBoolean("Sensor Filtered", () -> shooter.isSensorSeeing());

        // Flips the simulated sensor, which is what ShooterIOSimulation reports as isSensorSeeing
        tab.add("toggle shooter sensor", new InstantCommand(() ->
            SimulatedSensors.setSimulatedShooterSensorSeeing(!SimulatedSensors.isSimulatedShooterSensorSeeing())));
    }
}
