package frc.robot.subsystems.answers.L3;

import frc.robot.lib.OnyxMotorOutputs.CanBus;

public class ConveyanceConstants {
    public static final String CONVEYANCE_SUBSYSTEM_NAME = "Conveyance";
    public static final CanBus CONVEYANCE_CAN_BUS = CanBus.RIO;
    public static final String CONVEYANCE_MASTER_MOTOR_NAME = "ConveyanceMasterMotor";
    public static final String CONVEYANCE_FOLLOWER_MOTOR_NAME = "ConveyanceFollowerMotor";

    public static class ConveynaceConstantsRobot {
        public static final int ROBOT_CONVEYANCE_MASTER_MOTOR_ID = 16;
        public static final int ROBOT_CONVEYANCE_FOLLOWER_MOTOR_ID = 17;

        public static final int ROBOT_CONVEYANCE_SUPPLY_LIMIT = 40;
    }
}
