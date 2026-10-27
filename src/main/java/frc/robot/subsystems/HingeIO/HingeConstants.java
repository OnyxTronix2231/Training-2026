package frc.robot.subsystems.HingeIO;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.Unit;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanism2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import java.util.function.DoubleUnaryOperator;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class HingeConstants {
    public static final String HINGE_MOTOR_NAME = "Trigger Motor";
    public static final String SUBSYSTEM = "hinge";
    public static final double SUPPLY_LIMIT = 40;
    public static final double HINGE_DUTY_CYCLE_SPEED = 0.5;
    public static final DoubleUnaryOperator ROTATION_TO_DEGREES =rotation -> Units.rotationsToDegrees(rotation);

    public class RobotIOHingeConstants {
        public static final int HINGE_MASTER_MOTOR_ID = 1;
        public static final int HINGE_FOLLOWER_MOTOR_ID = 2;
    }

    public static class HingeSimulationConstants {
        public static final int SIMULATION_HINGE_MOTOR_ID = 1;
        public static final double SIMULATION_HINGE_LENGTH_METERS = 0.001;
        public static final double SIMULATION_HINGE_MASS_KG = 0.001;
        public static final int SIMULATION_HINGE_NUM_OF_MOTORS = 1;
        public static final double HINGE_SIMULATION_ROTATION_MULTIPLIER = 0.5;
        public static final double HINGE_X_POSITION = 3;
        public static final double HINGE_Y_POSITION = 1;
        public static final double HINGE_LIGAMENT_LENGTH=0.3;
        public static final double HINGE_LIGAMENT_ANGLE=180;
        public static final double HINGE_LIGAMENT_WIDTH=5;

        public static final LoggedMechanismRoot2d HINGE_ROOT = ROBOT_MECHANISM.getRoot(SUBSYSTEM, HINGE_X_POSITION, HINGE_Y_POSITION);
        public static final Color8Bit HINGE_LIGAMENT_COLOR = new Color8Bit(Color.kBlue);
        public static final LoggedMechanismLigament2d HINGE = HINGE_ROOT.append(
                new LoggedMechanismLigament2d(SUBSYSTEM,
                        HINGE_LIGAMENT_LENGTH,HINGE_LIGAMENT_ANGLE,
                        HINGE_LIGAMENT_WIDTH,HINGE_LIGAMENT_COLOR
                )
        );
    }

}
