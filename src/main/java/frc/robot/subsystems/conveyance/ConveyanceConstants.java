package frc.robot.subsystems.conveyance;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.subsystems.lecture.shooter.ShooterConstants.SHOOTER_SUBSYSTEM_NAME;
import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class ConveyanceConstants {
    public final static String CONVEYANCE_MASTER_MOTOR_NAME = "conveyanceMasterMotor";
    public final static String CONVEYANCE_FOLLOWER_MOTOR_NAME = "conveyanceFollowerMotor";
    public final static String CONVEYANCE_SUBSYSTEM_NAME = "Conveyance";

    public class ConveyanceRobotAConstants {
        public final static int ROBOT_A_MASTER_CONVEYANCE_MOTOR_ID = 10;
        public final static int ROBOT_A_FOLLOWER_CONVEYANCE_MOTOR_ID = 12;
        public final static int CONVEYANCE_SUPPLY_LIMIT = 40;
    }

    public class ConveyanceRobotBConstants {
        public final static int ROBOT_B_Conveyance_MOTOR_ID = 5;
        public final static int CONVEYANCE_SUPPLY_LIMIT = 40;
    }

    public class ConveyanceSimulationConstants {
        public final static int SIMULATION_CONVEYANCE_MOTOR_ID = 8;

        final static int SIMULATION_CONVEYANCE_NUM_OF_MOTORS = 2;
        public final static double SIMULATION_CONVEYANCE_LENGTH_METERS = 0.001;
        public final static double SIMULATION_CONVEYANCE_MASS_KG = 0.001;

        public static final double CONVEYANCE_X_POSITION = 3.4;
        public static final double CONVEYANCE_Y_POSITION = 1;

        public static final LoggedMechanismRoot2d CONVEYANCE_ROOT = ROBOT_MECHANISM.getRoot(SHOOTER_SUBSYSTEM_NAME, CONVEYANCE_X_POSITION, CONVEYANCE_Y_POSITION);
        public static final Color8Bit CONVEYANCE_LIGAMENT_COLOR = new Color8Bit(Color.kAqua);
        public static final double SIMULATION_ROTATION_MULTIPLIER = 0.5;

        public static final double CONVEYANCE_DUTY_CYCLE_SPEED = 0.5;
    }
}
