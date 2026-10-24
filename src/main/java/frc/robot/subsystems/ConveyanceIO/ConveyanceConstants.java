package frc.robot.subsystems.ConveyanceIO;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class ConveyanceConstants {
    public static final int CONVEYANCE_MASTER_MOTOR_ID = 1;
    public static final int CONVEYANCE_FOLLOWER_MOTOR_ID = 2;
    public static final String MASTER_Motor_Name = "MASTERCONVEYANCE";
    public static final String FOLLOWER_Motor_Name = "FOLLOWERCONVEYANCE";
    public static final String SUBSYSTEM = "CONVEYANCE";
    public static final double SUPPLY_LIMIT = 40;
    public static final double CONVEYANCE_DUTY_CYCLE_SPEED=0.5;

    public static class ConveyanceSimulationConstants {
        public static final int SIMULATION_CONVEYANCE_MOTOR_ID = 1;
        public static final double SIMULATION_CONVEYANCE_LENGTH_METERS = 0.001;
        public static final double SIMULATION_CONVEYANCE_MASS_KG = 0.001;
        public static final int SIMULATION_CONVEYANCE_NUM_OF_MOTORS = 2;
        public static final double CONVEYANCE_SIMULATION_ROTATION_MULTIPLIER = 0.5;
        public static final double CONVEYANCE_X_POSITION = 3;
        public static final double CONVEYANCE_Y_POSITION = 1;

        public static final LoggedMechanismRoot2d CONVEYANCE_ROOT = ROBOT_MECHANISM.getRoot(SUBSYSTEM, CONVEYANCE_X_POSITION, CONVEYANCE_Y_POSITION);

        public static final Color8Bit CONVEYANCE_LIGAMENT_COLOR = new Color8Bit(Color.kDarkRed);



    }


}
