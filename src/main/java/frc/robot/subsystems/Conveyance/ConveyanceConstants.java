package frc.robot.subsystems.Conveyance;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.visualization.VisualizedSubsystem;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;
import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;


public class ConveyanceConstants{

    public static final String CONVEYANCE_SUBSYSTEM_NAME = "Conveyance";

    public static class ConveyanceRobotAConstants{
        public static final int CONVEYANCE_SUPPLY_LIMIT = 40;
        public static final int CONVEYANCE_MASTER_MOTOR_ID = 11;
        public static final String CONVEYANCE_MASTER_MOTOR_NAME = "ConveyanceMaster";

        public static final int CONVEYANCE_FOLLOWER_MOTOR_ID = 13;
        public static final String CONVEYANCE_FOLLOWER_MOTOR_NAME = "ConveyanceFollower";
    }

    public static class ConveyanceSimulationConstants {
        public static final double CONVEYANCE_DUTY_CYCLE = 0.5;
        public static final double CONVEYANCE_X_POSITION = 3.4;
        public static final double CONVEYANCE_Y_POSITION = 1;

        public static final LoggedMechanismRoot2d CONVEYANCE_ROOT = ROBOT_MECHANISM.getRoot(CONVEYANCE_SUBSYSTEM_NAME,CONVEYANCE_X_POSITION,CONVEYANCE_Y_POSITION);
        public static final double SIMULATION_ROTATION_MULTIPLIER = 0.5;
        public static final Color8Bit CONVEYANCE_LIGAMENT_COLOR = new Color8Bit(Color.kDarkCyan);

        public static final int SIMULATION_CONVEYANCE_NUM_OF_MOTORS = 2;
        public static final int SIMULATION_CONVEYANCE_SUPPLY_LIMIT = 40;
        public static final String SIMULATION_CONVEYANCE_FOLLOWER_MOTOR_NAME = "ConveyanceFollower";

        public static final int SIMULATION_CONVEYANCE_MASTER_MOTOR_ID = 11;
        public static final String SIMULATION_CONVEYANCE_MASTER_MOTOR_NAME = "ConveyanceMaster";
    }
}
