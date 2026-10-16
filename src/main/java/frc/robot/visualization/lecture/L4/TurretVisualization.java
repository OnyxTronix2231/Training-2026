package frc.robot.visualization.lecture.L4;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.lecture.L4.Turret.TurretIO;
import frc.robot.visualization.VisualizedSubsystem;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.subsystems.lecture.L4.Turret.TurretConstants.TURRET_SUBSYSTEM_NAME;

public class TurretVisualization extends VisualizedSubsystem {
    private final TurretIO turret;

    public TurretVisualization(TurretIO turret) {
        this.turret = turret;
    }

    @Override
    protected void updateVisualization() {
        TurretVisualizationMechanism.TURRET.setAngle(turret.getTurretAngle());
    }

    public static class TurretVisualizationMechanism {
        private static final double TURRET_X_POSITION = 3.4;
        private static final double TURRET_Y_POSITION = 1;
        public static final LoggedMechanismRoot2d turretRoot = ROBOT_MECHANISM.getRoot(
            TURRET_SUBSYSTEM_NAME, TURRET_X_POSITION, TURRET_Y_POSITION);

        private static final double TURRET_LIGAMENT_LENGTH = 0.3;
        private static final double TURRET_LIGAMENT_ANGLE = 180;
        private static final double TURRET_LIGAMENT_LINE_WIDTH = 5;
        public static final Color8Bit TURRET_LIGAMENT_COLOR = new Color8Bit(Color.kRed);
        public static final LoggedMechanismLigament2d TURRET = turretRoot.append(
            new LoggedMechanismLigament2d(TURRET_SUBSYSTEM_NAME,
                TURRET_LIGAMENT_LENGTH, TURRET_LIGAMENT_ANGLE,
                TURRET_LIGAMENT_LINE_WIDTH, TURRET_LIGAMENT_COLOR
            )
        );
    }
}
