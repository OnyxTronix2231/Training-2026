package frc.robot.subsystems.Hinge;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanism2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import java.util.function.DoubleUnaryOperator;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class HingeConstants {
    public static final String HINGE_SUBSYSTEM_NAME = "Hinge";

    public static class Hinge_RobotAConstants {
        public static final int HINGE_MOTOR_ID = 5;
        public static final String HINGE_MOTOR_NAME = "HingeMotor";
    }
    public static class Hinge_Simulation_Constants {
        public static final double HINGE_LIGAMENT_LENGTH = 0.3;
        public static final double HINGE_LIGAMENT_ANGLE = 0;
        public static final double HINGE_LIGAMENT_WIDTH = 1;

        public static final Color8Bit HINGE_LIGAMENT_COLOR = new Color8Bit(Color.kDarkCyan);
        private static final double HINGE_X_POSITION = 3.4;
        private static final double HINGE_Y_POSITION = 1;
        public static final LoggedMechanismRoot2d root = ROBOT_MECHANISM.getRoot(
                HINGE_SUBSYSTEM_NAME, HINGE_X_POSITION, HINGE_Y_POSITION
        );
        public static final LoggedMechanismLigament2d HINGE = root.append(
                new LoggedMechanismLigament2d(
                        HINGE_SUBSYSTEM_NAME, HINGE_LIGAMENT_LENGTH, HINGE_LIGAMENT_ANGLE, HINGE_LIGAMENT_WIDTH, HINGE_LIGAMENT_COLOR));


        public static final DoubleUnaryOperator ROTATIONS_TO_DEGREES = rotations -> Units.degreesToRotations(rotations);
    }
}
