package frc.robot.visualization.lecture.L4;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.lecture.L4.LinearIntake.LinearIntakeIO;
import frc.robot.visualization.VisualizedSubsystem;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.subsystems.lecture.L4.LinearIntake.LinearIntakeConstants.LINEAR_INTAKE_SUBSYSTEM_NAME;

public class LinearIntakeVisualization extends VisualizedSubsystem {
    private final LinearIntakeIO linearIntake;

    public LinearIntakeVisualization(LinearIntakeIO linearIntake) {
        this.linearIntake = linearIntake;
    }

    @Override
    protected void updateVisualization() {
        LinearIntakeVisualizationMechanism.linearIntakeLigament.setLength(
            linearIntake.getPosition() + LinearIntakeVisualizationMechanism.MIN_LENGTH);
    }

    public static class LinearIntakeVisualizationMechanism {
        private static final double LINEAR_INTAKE_X_POSITION = 3;
        private static final double LINEAR_INTAKE_Y_POSITION = 0.2;
        public static final LoggedMechanismRoot2d linearIntakeRoot = ROBOT_MECHANISM.getRoot(
            LINEAR_INTAKE_SUBSYSTEM_NAME, LINEAR_INTAKE_X_POSITION, LINEAR_INTAKE_Y_POSITION);

        // Keeps the bar visible when the intake is fully closed
        public static final double MIN_LENGTH = 0.2;
        private static final double LINEAR_INTAKE_LIGAMENT_ANGLE = 180;
        private static final double LINEAR_INTAKE_LIGAMENT_LINE_WIDTH = 5;
        public static final Color8Bit COLOR = new Color8Bit(Color.kOrange);
        public static final LoggedMechanismLigament2d linearIntakeLigament = linearIntakeRoot.append(
            new LoggedMechanismLigament2d(LINEAR_INTAKE_SUBSYSTEM_NAME,
                MIN_LENGTH, LINEAR_INTAKE_LIGAMENT_ANGLE,
                LINEAR_INTAKE_LIGAMENT_LINE_WIDTH, COLOR
            )
        );
    }
}
