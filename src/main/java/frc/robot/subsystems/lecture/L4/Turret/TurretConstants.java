package frc.robot.subsystems.lecture.L4.Turret;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import java.util.function.DoubleUnaryOperator;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class TurretConstants {
    public static final String TURRET_SUBSYSTEM_NAME = "Turret";
    public static final String TURRET_MOTOR_NAME = "TurretMotor";

    public static final double TURRET_GEAR_RATIO = 10;

    public static final DoubleUnaryOperator ROTATIONS_TO_DEGREES = rotations -> Units.rotationsToDegrees(rotations / TURRET_GEAR_RATIO);

    public static class TurretSimulationConstants {
        public static final int SIMULATION_TURRET_MOTOR_ID = 40;

        public static final int SIMULATION_TURRET_NUM_OF_MOTORS = 1;

        public static final double SIMULATION_TURRET_LENGTH_METERS = 0.001;
        public static final double SIMULATION_TURRET_MASS_KG = 0.001;

        public static final double TURRET_X_POSITION = 3.4;
        public static final double TURRET_Y_POSITION = 1;
        public static final LoggedMechanismRoot2d TURRET_ROOT = ROBOT_MECHANISM.getRoot(
                TURRET_SUBSYSTEM_NAME, TURRET_X_POSITION, TURRET_Y_POSITION);

        public static final double TURRET_LIGAMENT_LENGTH = 0.3;
        public static final double TURRET_LIGAMENT_ANGLE = 180;
        public static final double TURRET_LIGAMENT_LINE_WIDTH = 5;
        public static final Color8Bit TURRET_LIGAMENT_COLOR = new Color8Bit(Color.kRed);
        public static final LoggedMechanismLigament2d TURRET = TURRET_ROOT.append(
                new LoggedMechanismLigament2d(TURRET_SUBSYSTEM_NAME,
                        TURRET_LIGAMENT_LENGTH, TURRET_LIGAMENT_ANGLE,
                        TURRET_LIGAMENT_LINE_WIDTH, TURRET_LIGAMENT_COLOR
                )
        );
    }
}
