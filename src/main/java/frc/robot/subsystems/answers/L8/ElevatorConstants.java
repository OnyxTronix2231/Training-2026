package frc.robot.subsystems.answers.L8;

import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.lib.PID.PIDValues;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import java.util.function.DoubleUnaryOperator;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class ElevatorConstants {
    public static final String ELEVATOR_SUBSYSTEM_NAME = "Elevator";

    public static final String ELEVATOR_MASTER_MOTOR_NAME = "ElevatorMasterMotor";
    public static final String ELEVATOR_FOLLOWER_MOTOR_NAME = "ElevatorFollowerMotor";

    public static final double ELEVATOR_FORWARD_SOFT_LIMIT_METERS = 1.2;
    public static final double ELEVATOR_REVERSE_SOFT_LIMIT_METERS = 0;

    public static final double ELEVATOR_DEFAULT_POSITION = 0;

    public static final double GEAR_RATIO = 56/9.0;

    public static final double ELEVATOR_ON_TARGET_DEBOUNCE_TIME = 0.3;
    public static final double ELEVATOR_ON_TARGET_TOLERANCE = 0.1;

    public static class ElevatorSimulationConstants {
        public static final double SIMULATION_GEAR_RADIUS = 1;
        public static final double SIMULATION_GEAR_CIRCUMFERENCE = 2 * Math.PI * SIMULATION_GEAR_RADIUS;

        public static double SIMULATION_ROTATIONS_TO_LENGTH(double rotations) {
            return (rotations / GEAR_RATIO) * SIMULATION_GEAR_CIRCUMFERENCE;
        }

        public static double SIMULATION_LENGTH_TO_ROTATIONS(double length) {
            return (length / SIMULATION_GEAR_CIRCUMFERENCE) * GEAR_RATIO;
        }

        public static final int SIMULATION_ELEVATOR_MOTOR_ID = 8;

        public static final double SIMULATION_ELEVATOR_LENGTH_METERS = 0.001;
        public static final double SIMULATION_ELEVATOR_MASS_KG = 0.001;
        public static final int SIMULATION_ELEVATOR_NUM_OF_MOTORS = 2;

        public static final DoubleUnaryOperator SIMULATION_ROTATIONS_TO_LENGTH = rotations -> SIMULATION_ROTATIONS_TO_LENGTH(rotations);

        public static final double SIMULATION_ELEVATOR_KP = 5;
        public static final double SIMULATION_ELEVATOR_KI = 0;
        public static final double SIMULATION_ELEVATOR_KD = 0;
        public static final double SIMULATION_ELEVATOR_KG = 0;
        public static final double SIMULATION_ELEVATOR_KS = 0;
        public static final double SIMULATION_ELEVATOR_KV = 0;
        public static final double SIMULATION_ELEVATOR_KA = 0;

        public static final PIDValues SIMULATION_ELEVATOR_PID_VALUES = new PIDValues(
            SIMULATION_ELEVATOR_KP,
            SIMULATION_ELEVATOR_KI,
            SIMULATION_ELEVATOR_KD,
            SIMULATION_ELEVATOR_KG,
            SIMULATION_ELEVATOR_KS,
            SIMULATION_ELEVATOR_KV,
            SIMULATION_ELEVATOR_KA,
            GravityTypeValue.Elevator_Static,
            StaticFeedforwardSignValue.UseVelocitySign
        );

        public static final double ELEVATOR_X_POSITION = 1;
        public static final double ELEVATOR_Y_POSITION = 1;
        public static final LoggedMechanismRoot2d ELEVATOR_ROOT = ROBOT_MECHANISM.getRoot(ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_X_POSITION, ELEVATOR_Y_POSITION);

        public static final double ELEVATOR_LIGAMENT_LENGTH = 0.3;
        public static final double ELEVATOR_LIGAMENT_ANGLE = 0;
        public static final double ELEVATOR_LIGAMENT_LINE_WIDTH = 5;
        public static final Color8Bit ELEVATOR_LIGAMENT_COLOR = new Color8Bit(Color.kRed);
        public static final LoggedMechanismLigament2d ELEVATOR = ELEVATOR_ROOT.append(
                new LoggedMechanismLigament2d(
                        "ELEVATOR",
                        ELEVATOR_LIGAMENT_LENGTH, ELEVATOR_LIGAMENT_ANGLE,
                        ELEVATOR_LIGAMENT_LINE_WIDTH, ELEVATOR_LIGAMENT_COLOR
                )
        );
    }

    public static class ElevatorRobotConstants {
        public static final int ROBOT_ELEVATOR_MASTER_MOTOR_ID = 11;
        public static final int ROBOT_ELEVATOR_FOLLOWER_MOTOR_ID = 12;

        public static final int ROBOT_ELEVATOR_SWITCH_ID = 13;
        public static final int ROBOT_ELEVATOR_CANCODER_ID = 14;

        public static final double ROBOT_ELEVATOR_CANCODER_OFFSET = 0;

        public static final double GEAR_RADIUS = 0.03;
        public static final double GEAR_CIRCUMFERENCE = 2 * Math.PI * GEAR_RADIUS;


        public static double ROTATIONS_TO_LENGTH(double rotations) {
            return (rotations / GEAR_RATIO) * GEAR_CIRCUMFERENCE;
        }

        public static double LENGTH_TO_ROTATIONS(double length) {
            return (length / GEAR_CIRCUMFERENCE) * GEAR_RATIO;
        }

        public static final DoubleUnaryOperator ROTATIONS_TO_LENGTH = rotations -> ROTATIONS_TO_LENGTH(rotations);

        public static final double ROBOT_ELEVATOR_KP = 0;
        public static final double ROBOT_ELEVATOR_KI = 0;
        public static final double ROBOT_ELEVATOR_KD = 0;
        public static final double ROBOT_ELEVATOR_KG = 0;
        public static final double ROBOT_ELEVATOR_KS = 0;
        public static final double ROBOT_ELEVATOR_KV = 0.12;
        public static final double ROBOT_ELEVATOR_KA = 0;

        public static final PIDValues ROBOT_ELEVATOR_PID_VALUES = new PIDValues(
            ROBOT_ELEVATOR_KP,
            ROBOT_ELEVATOR_KI,
            ROBOT_ELEVATOR_KD,
            ROBOT_ELEVATOR_KG,
            ROBOT_ELEVATOR_KS,
            ROBOT_ELEVATOR_KV,
            ROBOT_ELEVATOR_KA,
            GravityTypeValue.Elevator_Static,
            StaticFeedforwardSignValue.UseVelocitySign
        );
    }
}
