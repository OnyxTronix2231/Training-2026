package frc.robot.subsystems.Trigger;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class TriggerConstants {
    public final static String TRIGGER_SUBSYSTEM_NAME = "Trigger";
    public final static String TRIGGER_MOTOR_NAME = "Trigger motor";
    public static final String TRIGGER_VISUALIZATION_SUBSYSTEM_NAME = "Shooter";

    public class TriggerSimulationConstants{
        public final static int SIMULATION_TRIGGER_MOTOR_ID = 58;
        public final static int SIMULATION_TRIGGER_NUM_OF_MOTOR = 1;
        public final static double SIMULATION_TRIGGER_LENGTH_METERS = 0.001;
        public final static double SIMULATION_TRIGGER_MASS_KG = 0.001;

    }

    public class TriggerVisualizationConstants{
        public static final double TRIGGER_X_POSITION = 3;
        public static final double TRIGGER_Y_POSITION = 1;
        public static final double SIMULATION_ROTATION_MULTIPLIER = 0.5;


        public static final LoggedMechanismRoot2d TRIGGER_ROOT = ROBOT_MECHANISM.getRoot(TRIGGER_SUBSYSTEM_NAME, TRIGGER_X_POSITION, TRIGGER_Y_POSITION);

        public static final Color8Bit TRIGGER_LIGAMENT_COLOR = new Color8Bit(Color.kBlack);


    }
}
