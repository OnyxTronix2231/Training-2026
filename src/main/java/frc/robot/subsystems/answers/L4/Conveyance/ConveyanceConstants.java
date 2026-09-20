package frc.robot.subsystems.answers.L4.Conveyance;

public class ConveyanceConstants {


    public static final String CONVEYANCE_SUBSYSTEM_NAME = "Conveyance";
    public static final String CONVEYANCE_MASTER_MOTOR_NAME = "ConveyanceMasterMotor";
    public static final String CONVEYANCE_FOLLOWER_MOTOR_NAME = "ConveyanceFollowerMotor";


    public static class ConveynaceConstantsRobot {
        public static final int ROBOT_CONVEYANCE_MASTER_MOTOR_ID = 16;
        public static final int ROBOT_CONVEYANCE_FOLLOWER_MOTOR_ID = 17;

        public static final int ROBOT_CONVEYANCE_SENSOR_ID = 20;
        public static final int ROBOT_CONVEYANCE_SUPPLY_LIMIT = 40;
    }

    public static class ConveyanceConstantsSimulation {
        public static final int SIMULATION_CONVEYANCE_MASTER_MOTOR_ID = 16;
        public static final int SIMULATION_CONVEYANCE_FOLLOWER_MOTOR_ID = 17;

        public static final int SIMULATION_CONVEYANCE_NUM_OF_MOTORS = 2;
        public static final double SIMULATION_CONVEYANCE_LENGTH_METERS = 0.001;
        public static final double SIMULATION_CONVEYANCE_MASS_KG = 0.001;

        public static final double CONVEYANCE_VISUALIZATION_MULTIPLIER = 0.5;
    }
}
