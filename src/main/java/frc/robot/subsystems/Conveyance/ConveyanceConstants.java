package frc.robot.subsystems.Conveyance;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.subsystems.lecture.shooter.ShooterConstants.SHOOTER_SUBSYSTEM_NAME;
import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class ConveyanceConstants {
    public final static String CONVEYANCE_SUBSYSTEM_NAME = "Conveyance";
    public final static String CONVEYANCE_MASTER_MOTOR_NAME = "Conveyance motor";
    public final static String CONVEYANCE_FOLLOWER_MOTOR_NAME = "Conveyance follower motor";
    public static final String CONVEYANCE_VISUALIZATION_SUBSYSTEM_NAME = "Shooter";




    public class ConveyanceRobotConstants{
        public final static int ROBOT_CONVEYANCE_MOTOR_ID = 1;
        public final static int ROBOT_CONVEYANCE_FOLLOWER_MOTOR_ID = 2;
        public final static double R0B0T_CONVEYANCE_DUTY_CYCLE = 0.5;
        public static final int ROBOT_Conveyance_SUPPLY_LIMIT = 40;

    }

    public class ConveyanceSimulationConstants{
        public final static int SIMULATION_CONVEYANCE_MOTOR_ID = 58;
        public final static int SIMULATION_CONVEYANCE_NUM_OF_MOTOR = 2;
        public final static double SIMULATION_CONVEYANCE_LENGTH_METERS = 0.001;
        public final static double SIMULATION_CONVEYANCE_MASS_KG = 0.001;

    }
    public class ConveyanceVisualizationConstants{
        public static final double CONVEYANCE_X_POSITION = 3;
        public static final double CONVEYANCE_Y_POSITION = 1;
        public static final double SIMULATION_ROTATION_MULTIPLIER = 0.5;


        public static final LoggedMechanismRoot2d CONVEYANCE_ROOT = ROBOT_MECHANISM.getRoot(CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_X_POSITION, CONVEYANCE_Y_POSITION);

        public static final Color8Bit CONVEYANCE_LIGAMENT_COLOR = new Color8Bit(Color.kBlack);


    }
}
