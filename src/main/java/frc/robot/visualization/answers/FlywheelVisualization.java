package frc.robot.visualization.answers;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.answers.L4.Flywheel.FlywheelIOSimulation;
import frc.robot.visualization.VisualizedSubsystem;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.lib.VisualizationLib.*;
import static frc.robot.subsystems.answers.L4.Flywheel.FlywheelConstants.FLYWHEEL_SUBSYSTEM_NAME;
import static frc.robot.subsystems.answers.L4.Flywheel.FlywheelConstants.FLYWHEEL_VISUALIZATION_MULTIPLIER;

public class FlywheelVisualization extends VisualizedSubsystem {
    FlywheelIOSimulation flywheelIOSimulation;
    public FlywheelVisualization(FlywheelIOSimulation flywheelIOSimulation) {
        this.flywheelIOSimulation = flywheelIOSimulation;

        generateVisualizedWheel(
            FlywheelVisualizationMechanism.FLYWHEEL, FlywheelVisualizationMechanism.FLYWHEEL_WHEEL_PART_COUNT,
            FlywheelVisualizationMechanism.flywheelRoot, FLYWHEEL_SUBSYSTEM_NAME, FlywheelVisualizationMechanism.FLYWHEEL_LIGAMENT_COLOR
        );
    }

    @Override
    protected void updateVisualization() {
        updateVisualizedWheel(FlywheelVisualizationMechanism.FLYWHEEL,
            flywheelIOSimulation.getMotorInputs().getMotorAngularVelocityRotPerSec(), FLYWHEEL_VISUALIZATION_MULTIPLIER);
    }

    public class FlywheelVisualizationMechanism {
        private static final double FLYWHEEL_X_POSITION = 3.17;
        private static final double FLYWHEEL_Y_POSITION = 0.68;
        public static final int FLYWHEEL_WHEEL_PART_COUNT = 4;
        public static final LoggedMechanismRoot2d flywheelRoot = ROBOT_MECHANISM.getRoot(FLYWHEEL_SUBSYSTEM_NAME, FLYWHEEL_X_POSITION, FLYWHEEL_Y_POSITION);

        private static final Color8Bit FLYWHEEL_LIGAMENT_COLOR = new Color8Bit(Color.kYellow);

        public static final LoggedMechanismLigament2d[] FLYWHEEL = new LoggedMechanismLigament2d[4];
    }
}
