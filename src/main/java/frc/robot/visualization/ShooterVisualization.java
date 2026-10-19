package frc.robot.visualization;

import frc.robot.lib.VisualizationLib.Wheel;
import frc.robot.subsystems.lecture.shooter.ShooterIO;
import frc.robot.subsystems.lecture.shooter.ShooterIOSimulation;

import static frc.robot.subsystems.lecture.shooter.ShooterConstants.SHOOTER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.ShooterSimulationConstants.*;

public class ShooterVisualization extends VisualizedSubsystem {

    private final ShooterIO shooterIO;
    private final Wheel wheel;

    public ShooterVisualization(ShooterIO shooterIO) {
        this.shooterIO =  shooterIO;

        wheel = new Wheel(SHOOTER_ROOT, SHOOTER_SUBSYSTEM_NAME, SHOOTER_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        wheel.spin(shooterIO.getRPS() * SIMULATION_ROTATION_MULTIPLIER);
    }
}
