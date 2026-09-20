package frc.robot.subsystems.answers.L4.Elevator;

import java.util.function.DoubleUnaryOperator;

public class ElevatorConstants {
    public static final int ELEVATOR_MASTER_MOTOR_ID = 21;
    public static final int ELEVATOR_FOLLOWER_MOTOR_ID = 22;

    public static final int ELEVATOR_SWITCH_ID = 1;

    public static final String ELEVATOR_SUBSYSTEM_NAME = "Elevator";

    public static final String ELEVATOR_MASTER_MOTOR_NAME = "ElevatorMasterMotor";
    public static final String ELEVATOR_FOLLOWER_MOTOR_NAME = "ElevatorFollowerMotor";

    public static final double ELEVATOR_FORWARD_SOFT_LIMIT_METERS = 1.2;
    public static final double ELEVATOR_REVERSE_SOFT_LIMIT_METERS = 0;
    public static final double GEAR_RATIO = 56/9.0;

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
}
