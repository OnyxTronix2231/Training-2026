package frc.robot.subsystems.Hinge;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import java.awt.*;
import java.util.function.DoubleUnaryOperator;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class HingeConstants {

    public static final String HINGE_NAME = "Hinge";

    public static class HingeSimulationConstants {
        public static final String HINGE_MOTOR_NAME = "HingeMotor";

        public static final String HINGE_CANBUS = "DriveTrain";

        public static final int HINGE_MOTOR_ID = 2;

        public static final int SIMULATION_HINGE_NUM_OF_MOTORS = 1;
        public static final int SIMULATION_HINGE_GEARING = 1;
        public static final double SIMULATION_HINGE_LENGTH_METERS = 0.001;
        public static final double SIMULATION_HINGE_MASS_KG = 0.001;
        public static final int HINGE_FORWARD_SOFT_LIMIT_ANGLE = 180;
        public static final int HINGE_REVERSE_SOFT_LIMIT_ANGLE = 0;
        private static final double HINGE_X_POSITION = 2;
        private static final double HINGE_Y_POSITION = 3.4;
        public static final LoggedMechanismRoot2d HINGE_ROOT = ROBOT_MECHANISM.getRoot(HINGE_NAME,
                HINGE_X_POSITION, HINGE_Y_POSITION);
        private static final double HINGE_LIGAMENT_LENGTH = 0.4;
        private static final double HINGE_LIGAMENT_ANGLE = 180;
        private static final double HINGE_LIGAMENT_WIDTH = 5;
        public static final Color8Bit HINGE_LIGAMENT_COLOR = new Color8Bit(Color.kOrange);
        public static final LoggedMechanismLigament2d HINGE_LIGAMENT = HINGE_ROOT.append(
                new LoggedMechanismLigament2d(HINGE_NAME, HINGE_LIGAMENT_LENGTH,
                        HINGE_LIGAMENT_ANGLE, HINGE_LIGAMENT_WIDTH, HINGE_LIGAMENT_COLOR));
        public static final double HINGE_DUTY_CYCLE_ANGLE = 0.005;
        public static final double HINGE_SUTY_CYCLE_SPEED = 0.005;

        public static final DoubleUnaryOperator ROTATIONS_TO_ANGLE = (rotations) -> Units.rotationsToDegrees(rotations);

        public static double SIMULATION_ANGLE_TO_ROTATIONS(double angle) {
            return Units.degreesToRotations(angle);
        }
    }
}
