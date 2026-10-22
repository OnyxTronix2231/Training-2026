package frc.robot.visualization.lecture.L4;

import frc.robot.lib.VisualizationLib.Wheel;
import frc.robot.subsystems.lecture.L4.Shooter.ShooterIO;
import frc.robot.visualization.VisualizedSubsystem;

import static frc.robot.subsystems.lecture.L4.Shooter.ShooterConstants.SHOOTER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.lecture.L4.Shooter.ShooterConstants.ShooterSimulationConstants.*;

public class ShooterVisualization extends VisualizedSubsystem {
    private final ShooterIO shooter;
    private final Wheel wheel;

    public ShooterVisualization(ShooterIO shooter) {
        this.shooter = shooter;
        wheel = new Wheel(SHOOTER_ROOT, SHOOTER_SUBSYSTEM_NAME, SHOOTER_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        double degrees = shooter.getRPS() * SIMULATION_ROTATION_MULTIPLIER;
        wheel.spin(degrees);
    }
}
