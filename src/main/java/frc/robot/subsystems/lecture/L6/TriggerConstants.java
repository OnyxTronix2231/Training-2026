package frc.robot.subsystems.lecture.L6;

public class TriggerConstants {
    public static final String TRIGGER_SUBSYSTEM_NAME = "Trigger";

    public static final String TRIGGER_MOTOR_NAME = "TriggerMotor";

    // 3 robot cycles of 20ms
    public static final double TRIGGER_SENSOR_DEBOUNCE_TIME = 0.06;
    public static final double TRIGGER_DEBOUNCE_TIME = 0.1;

    public static final double TRIGGER_TOLERANCE = 2;

    public static final double TRIGGER_DEFAULT_VELOCITY = 50;
    public static final double TRIGGER_DEFAULT_DUTY_CYCLE = 0.5;

    public static class TriggerSimulationConstants {
        public static final int SIMULATION_TRIGGER_MOTOR_ID = 11;

        public static final int SIMULATION_TRIGGER_NUM_OF_MOTORS = 1;

        public static final double SIMULATION_TRIGGER_LENGTH_METERS = 0.001;
        public static final double SIMULATION_TRIGGER_MASS_KG = 0.001;
    }

    public static class TriggerRobotConstants {
        public static final int ROBOT_TRIGGER_MOTOR_ID = 18;

        public static final int ROBOT_TRIGGER_SENSOR_ID = 9;
    }
}
