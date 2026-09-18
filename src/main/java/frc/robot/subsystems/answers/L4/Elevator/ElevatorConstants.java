package frc.robot.subsystems.answers.L4.Elevator;

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
    public static final double GEAR_RADIUS = 0.03;
    public static final double GEAR_CIRCUMFERENCE = 2 * Math.PI * GEAR_RADIUS;

    public static final double GEAR_RADIUS_SIMULATION = 1;
    public static final double GEAR_CIRCUMFERENCE_SIMULATION = 2 * Math.PI * GEAR_RADIUS_SIMULATION;

    public static double ROTATIONS_TO_LENGTH(double rotations, boolean isSimulation) {
        if (isSimulation) {
            return ROTATIONS_TO_LENGTH(rotations, GEAR_CIRCUMFERENCE_SIMULATION, GEAR_RATIO);
        }
        return ROTATIONS_TO_LENGTH(rotations, GEAR_CIRCUMFERENCE, GEAR_RATIO);
    }

    public static double LENGTH_TO_ROTATIONS(double length, boolean isSimulation) {
        if (isSimulation) {
            return LENGTH_TO_ROTATIONS(length, GEAR_CIRCUMFERENCE_SIMULATION, GEAR_RATIO);
        }
        return LENGTH_TO_ROTATIONS(length, GEAR_CIRCUMFERENCE, GEAR_RATIO);
    }

    private static double ROTATIONS_TO_LENGTH(double rotations, double circumference, double ratio) {
        return (rotations / ratio) * circumference;
    }

    private static double LENGTH_TO_ROTATIONS(double length, double circumference, double ratio) {
        return (length / circumference) * ratio;
    }

    public static final int SIMULATION_ELEVATOR_MOTOR_ID = 8;

    public static final double SIMULATION_ELEVATOR_LENGTH_METERS = 0.001;
    public static final double SIMULATION_ELEVATOR_MASS_KG = 0.001;
    public static final int SIMULATION_ELEVATOR_NUM_OF_MOTORS = 2;

}
