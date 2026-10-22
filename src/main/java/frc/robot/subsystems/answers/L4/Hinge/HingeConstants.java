package frc.robot.subsystems.answers.L4.Hinge;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import java.util.function.DoubleUnaryOperator;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class HingeConstants {

    public static final String HINGE_SUBSYSTEM_NAME = "Hinge";
    public static final String HINGE_MOTOR_NAME = "HingeMotor";
    public static class HingeSimulationConstants {
        public static final int SIMULATION_HINGE_MOTOR_ID = 10;

        public static final int SIMULATION_HINGE_NUM_OF_MOTORS = 1;

        public static final double SIMULATION_HINGE_LENGTH_METERS = 0.001;
        public static final double SIMULATION_HINGE_MASS_KG = 0.001;

        public static final double SIMULATION_HINGE_FORWARD_SOFT_LIMIT_DEGREES = 180;
        public static final double SIMULATION_HINGE_REVERSE_SOFT_LIMIT_DEGREES = 0;

        public static final DoubleUnaryOperator ROTATIONS_TO_ANGLE = (rotations) -> Units.rotationsToDegrees(rotations);

        public static final double HINGE_X_POSITION = 3.17;
        public static final double HINGE_Y_POSITION = 0.68;
        public static final double HINGE_LIGAMENT_LENGTH = 0.3;
        public static final double HINGE_LIGAMENT_ANGLE = 0;
        public static final int HINGE_LIGAMENT_LINE_WIDTH = 4;
        public static final LoggedMechanismRoot2d HINGE_ROOT = ROBOT_MECHANISM.getRoot(HINGE_SUBSYSTEM_NAME, HINGE_X_POSITION, HINGE_Y_POSITION);

        public static final Color8Bit HINGE_LIGAMENT_COLOR = new Color8Bit(Color.kBlue);

        public static final LoggedMechanismLigament2d HINGE = HINGE_ROOT.append(
                new LoggedMechanismLigament2d(
                        "HINGE",
                        HINGE_LIGAMENT_LENGTH, HINGE_LIGAMENT_ANGLE,
                        HINGE_LIGAMENT_LINE_WIDTH, HINGE_LIGAMENT_COLOR
                )
        );
    }
}
