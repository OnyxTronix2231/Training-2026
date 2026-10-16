package frc.robot.visualization.lecture.L4;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.lib.VisualizationLib.Wheel;
import frc.robot.subsystems.lecture.L4.Shooter.ShooterIO;
import frc.robot.visualization.VisualizedSubsystem;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.subsystems.lecture.L4.Shooter.ShooterConstants.SHOOTER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.lecture.L4.Shooter.ShooterConstants.ShooterSimulationConstants.SIMULATION_ROTATION_MULTIPLIER;

public class ShooterVisualization extends VisualizedSubsystem {
    private final ShooterIO shooter;
    private final Wheel wheel;

    public ShooterVisualization(ShooterIO shooter) {
        this.shooter = shooter;
        wheel = new Wheel(ShooterVisualizationMechanism.SHOOTER_ROOT, SHOOTER_SUBSYSTEM_NAME, ShooterVisualizationMechanism.SHOOTER_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        double degrees = shooter.getRPS() * SIMULATION_ROTATION_MULTIPLIER;
        wheel.spin(degrees);
    }

    public static class ShooterVisualizationMechanism {
        private static final double SHOOTER_X_POSITION = 3.4;
        private static final double SHOOTER_Y_POSITION = 1;
        public static final LoggedMechanismRoot2d SHOOTER_ROOT = ROBOT_MECHANISM.getRoot(
            SHOOTER_SUBSYSTEM_NAME, SHOOTER_X_POSITION, SHOOTER_Y_POSITION);

        static final Color8Bit SHOOTER_LIGAMENT_COLOR = new Color8Bit(Color.kDarkRed);
    }
}
