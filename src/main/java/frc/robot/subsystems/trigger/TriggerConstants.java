package frc.robot.subsystems.trigger;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class TriggerConstants {
    public final static String TRIGGER_MOTOR_NAME = "triggerMotor";
    public final static String TRIGGER_SUBSYSTEM_NAME = "trigger";

    public class TriggerRobotConstants {
        public final static int ROBOT_TRIGGER_MOTOR_ID = 5;
        public final static int TRIGGER_SUPPLY_LIMIT = 40;
    }

    public class TriggerSimulationConstants {
        public final static int SIMULATION_TRIGGER_MOTOR_ID = 8;

        final static int SIMULATION_TRIGGER_NUM_OF_MOTORS = 2;
        public final static double SIMULATION_TRIGGER_LENGTH_METERS = 0.001;
        public final static double SIMULATION_TRIGGER_MASS_KG = 0.001;

        public static final double TRIGGER_X_POSITION = 3.4;
        public static final double TRIGGER_Y_POSITION = 1;

        public static final LoggedMechanismRoot2d TRIGGER_ROOT = ROBOT_MECHANISM.getRoot(TRIGGER_SUBSYSTEM_NAME, TRIGGER_X_POSITION, TRIGGER_Y_POSITION);
        public static final Color8Bit TRIGGER_LIGAMENT_COLOR = new Color8Bit(Color.kAqua);
        public static final double SIMULATION_ROTATION_MULTIPLIER = 0.5;

        public static final double TRIGGER_DUTY_CYCLE_SPEED = 0.5;
    }
}
