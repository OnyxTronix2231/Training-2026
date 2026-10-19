package frc.robot.subsystems.lecture.elevator;

import java.util.function.DoubleUnaryOperator;

public class ElevatorConstants {
    public static final String ELEVATOR_SUBSYSTEM_NAME = "Elevator";
    public static final String ELEVATOR_MOTOR_NAME = "ElevatorMotor";

    public static class ElevatorRobotBConstants {
        public static final int ROBOT_B_ELEVATOR_MOTOR_ID = 1;

        public static final double ROBOT_B_ELEVATOR_METERS_PER_ROTATION = 0.05;

        public static  double ROBOT_B_ELEVATOR_ROTATIONS_TO_METERS(double rotations) {
            return rotations * ROBOT_B_ELEVATOR_METERS_PER_ROTATION;
        }

        public static double ROBOT_B_ELEVATOR_METERS_TO_ROTATIONS(double meters) {
            return meters / ROBOT_B_ELEVATOR_METERS_PER_ROTATION;
        }

        public static final DoubleUnaryOperator ROBOT_B_ELEVATOR_ROTATIONS_TO_METERS = (rotations) -> ROBOT_B_ELEVATOR_ROTATIONS_TO_METERS(rotations);
    }

    public static class ElevatorRobotAConstants {
        public static final int ROBOT_A_ELEVATOR_MASTER_MOTOR_ID = 13;
        public static final int ROBOT_A_ELEVATOR_FOLLOWER_MOTOR_ID = 14;

        public static final double ROBOT_A_ELEVATOR_METERS_PER_ROTATION = 0.05;

        public static  double ROBOT_A_ELEVATOR_ROTATIONS_TO_METERS(double rotations) {
            return rotations * ROBOT_A_ELEVATOR_METERS_PER_ROTATION;
        }

        public static double ROBOT_A_ELEVATOR_METERS_TO_ROTATIONS(double meters) {
            return meters / ROBOT_A_ELEVATOR_METERS_PER_ROTATION;
        }

        public static final DoubleUnaryOperator ROBOT_A_ELEVATOR_ROTATIONS_TO_METERS = (rotations) -> ROBOT_A_ELEVATOR_ROTATIONS_TO_METERS(rotations);
    }
}
