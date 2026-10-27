package frc.robot.subsystems.Hinge;

import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import java.util.function.DoubleUnaryOperator;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class HingeConstants {
    public final static String HINGE_SUBSYSTEM_NAME = "Hinge";
    public final static String HINGE_MOTOR_NAME = "Hinge motor";
    public static final double HINGE_MAX_RPS = 30;
    public static final double HINGE_SPEED = 0.4;
    public static final double HINGE_FORWARD_SOFT_LIMIT_DEGREES = 180;
    public static final double HINGE_REVERSE_SOFT_LIMIT_DEGREES = 0;
    public static final DoubleUnaryOperator HINGE_ROTATIONS_TO_DEGREES = (rotations) -> Units.radiansToDegrees(rotations);



    public static class HingeSimulationConstants{
        public final static int SIMULATION_HINGE_MOTOR_ID = 57;
        public final static int SIMULATION_HINGE_NUM_OF_MOTOR = 1;
        public final static double SIMULATION_HINGE_LENGTH_METERS = 0.001;
        public final static double SIMULATION_HINGE_MASS_KG = 0.001;

        public static final double TRIGGER_X_POSITION = 3;
        public static final double TRIGGER_Y_POSITION = 1;
        public static final double HINGE_SIMULATION_ROTATION_MULTIPLIER = 0.5;

        public static final double HINGE_LIGAMENT_LENGTH = 0.3;
        public static final double HINGE_LIGAMENT_ANGLE = 180;
        public static final double HINGE_LIGAMENT_LINE_WIDTH = 5;
        public static final LoggedMechanismRoot2d HINGE_ROOT = ROBOT_MECHANISM.getRoot(HINGE_SUBSYSTEM_NAME, TRIGGER_X_POSITION, TRIGGER_Y_POSITION);
        public static final Color8Bit HINGE_LIGAMENT_COLOR = new Color8Bit(Color.kMagenta);
        public static final LoggedMechanismLigament2d HINGE = HINGE_ROOT.append(
                new LoggedMechanismLigament2d(HINGE_SUBSYSTEM_NAME,
                        HINGE_LIGAMENT_LENGTH, HINGE_LIGAMENT_ANGLE,
                        HINGE_LIGAMENT_LINE_WIDTH, HINGE_LIGAMENT_COLOR)
        );
    }

}
