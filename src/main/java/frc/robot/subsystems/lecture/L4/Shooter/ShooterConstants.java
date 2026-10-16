package frc.robot.subsystems.lecture.L4.Shooter;

public class ShooterConstants {
    public static final String SHOOTER_SUBSYSTEM_NAME = "Shooter";
    public static final String SHOOTER_MASTER_MOTOR_NAME = "ShooterMasterMotor";

    public static class ShooterRobotConstants {
        public static final int ROBOT_SHOOTER_MOTOR_ID = 13;

        public static final double ROBOT_SHOOTER_CONVERSION_RATE = 36 / 24.0;
    }

    public static class ShooterSimulationConstants {
        public static final int SIMULATION_SHOOTER_MASTER_MOTOR_ID = 39;

        public static final int SIMULATION_SHOOTER_NUM_OF_MOTORS = 1;

        public static final double SIMULATION_SHOOTER_LENGTH_METERS = 0.001;
        public static final double SIMULATION_SHOOTER_MASS_KG = 0.001;

        public static final double SIMULATION_ROTATION_MULTIPLIER = 0.5;
    }
}
