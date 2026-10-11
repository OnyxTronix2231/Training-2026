package frc.robot.subsystems.answers.L6;

public class TriggerConstants {
    public static final String TRIGGER_SUBSYSTEM_NAME = "Trigger";

    public static final String TRIGGER_MOTOR_NAME = "TriggerMotor";

    public static final double TRIGGER_DEFAULT_VELOCITY = 50;

    public static class TriggerSimulationConstants {
        public static final int SIMULATION_TRIGGER_MOTOR_ID = 11;

        public static final int SIMULATION_TRIGGER_NUM_OF_MOTORS = 1;

        public static final double SIMULATION_TRIGGER_LENGTH_METERS = 0.001;
        public static final double SIMULATION_TRIGGER_MASS_KG = 0.001;

        public static final double TRIGGER_VISUALIZATION_MULTIPLIER = 0.5;

        public static final double SIMULATION_TRIGGER_DEBOUNCE_TIME = 0.3;
    }

    public static class TriggerRobotConstants {
        public static final int ROBOT_TRIGGER_MOTOR_ID = 18;

        public static final int ROBOT_TRIGGER_SENSOR_ID = 9;

        public static final double ROBOT_TRIGGER_DEBOUNCE_TIME = 0.3;
    }
}
