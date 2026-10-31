package frc.robot.subsystems.Hinge;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import java.util.function.DoubleUnaryOperator;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class HingeConstants {
    
    public static final String HINGE_SUBSYSTEM_NAME = "Hinge";
    public static final String HINGE_MOTOR_NAME = "Hinge";
    public static final double HINGE_DUTY_CYCLE_SPEED = 0.005;

    public static class HingeRobotAConstants {

        public static final int ROBOT_A_HINGE_MOTOR_ID = 1;

        public static final double HINGE_FORWARD_SOFT_LIMIT = 180;
        public static final double HINGE_REVERSE_SOFT_LIMIT = 0;

        public static final DoubleUnaryOperator ROTATIONS_TO_ANGLE = (rotations) -> Units.rotationsToDegrees(rotations);
    }

    public static class HingeSimulationConstants {

        public static final int SIMULATION_HINGE_MOTOR_ID = 1;

        public static final double SIMULATION_HINGE_LENGTH_METERS = 0.001;
        public static final double SIMULATION_HINGE_MASS_KG = 0.001;

        public static final int SIMULATION_HINGE_NUM_OF_MOTORS = 1;
        public static final int SIMULATION_HINGE_GEARING = 1;

        public static final double HINGE_LIGAMENT_LENGTH = 0.7;
        public static final double HINGE_LIGAMENT_LINE_WIDTH = 2;
        public static final double HINGE_LIGAMENT_STARTING_POS = 0;

        public static final double HINGE_FORWARD_SOFT_LIMIT_METERS = 180;
        public static final double HINGE_REVERSE_SOFT_LIMIT_METERS = 0;

        public static final double HINGE_X_POSITION = 1;
        public static final double HINGE_Y_POSITION = 1;

        public static final Color8Bit HINGE_LIGAMENT_COLOR = new Color8Bit(Color.kDarkGreen);

        public static final LoggedMechanismRoot2d HINGE_ROOT = ROBOT_MECHANISM.getRoot(HINGE_SUBSYSTEM_NAME, HINGE_X_POSITION, HINGE_Y_POSITION);

        public static final LoggedMechanismLigament2d HINGE = HINGE_ROOT.append(new LoggedMechanismLigament2d(HINGE_SUBSYSTEM_NAME, HINGE_LIGAMENT_LENGTH, HINGE_LIGAMENT_STARTING_POS, HINGE_LIGAMENT_LINE_WIDTH, HINGE_LIGAMENT_COLOR));

        public static final DoubleUnaryOperator ROTATIONS_TO_ANGLE = (rotations) -> Units.rotationsToDegrees(rotations);
    }
}
