package frc.robot.subsystems.answers.L4.Conveyance;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class ConveyanceConstants {


    public static final String CONVEYANCE_SUBSYSTEM_NAME = "Conveyance";
    public static final String CONVEYANCE_MASTER_MOTOR_NAME = "ConveyanceMasterMotor";
    public static final String CONVEYANCE_FOLLOWER_MOTOR_NAME = "ConveyanceFollowerMotor";


    public static class ConveyanceRobotConstants {
        public static final int ROBOT_CONVEYANCE_MASTER_MOTOR_ID = 16;
        public static final int ROBOT_CONVEYANCE_FOLLOWER_MOTOR_ID = 17;

        public static final int ROBOT_CONVEYANCE_SUPPLY_LIMIT = 40;
    }

    public static class ConveyanceSimulationConstants {
        public static final int SIMULATION_CONVEYANCE_MASTER_MOTOR_ID = 16;

        public static final int SIMULATION_CONVEYANCE_NUM_OF_MOTORS = 2;
        public static final double SIMULATION_CONVEYANCE_LENGTH_METERS = 0.001;
        public static final double SIMULATION_CONVEYANCE_MASS_KG = 0.001;

        public static final double CONVEYANCE_VISUALIZATION_MULTIPLIER = 0.5;

        public static final double CONVEYANCE_X_POSITION = 3.17;
        public static final double CONVEYANCE_Y_POSITION = 0.68;
        public static final LoggedMechanismRoot2d CONVEYANCE_ROOT = ROBOT_MECHANISM.getRoot(CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_X_POSITION, CONVEYANCE_Y_POSITION);

        public static final Color8Bit CONVEYANCE_LIGAMENT_COLOR = new Color8Bit(Color.kYellow);
    }
}
