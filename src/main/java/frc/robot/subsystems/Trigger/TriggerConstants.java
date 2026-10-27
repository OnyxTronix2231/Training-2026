package frc.robot.subsystems.Trigger;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class TriggerConstants {

    public static final String HINGE_MOTOR_NAME = "Trigger Motor";
    public static final String SUBSYSTEM = "trigger";
    public static final double SUPPLY_LIMIT = 40;
    public static final double TRIGGER_DUTY_CYCLE_SPEED = 0.5;

    public class RobotIOHingeConstants {
        public static final int HINGE_MASTER_MOTOR_ID = 1;
        public static final int HINGE_FOLLOWER_MOTOR_ID = 2;
    }

    public static class TriggerSimulationConstants {
        public static final int SIMULATION_TRIGGER_MOTOR_ID = 1;
        public static final double SIMULATION_TRIGGER_LENGTH_METERS = 0.001;
        public static final double SIMULATION_TRIGGER_MASS_KG = 0.001;
        public static final int SIMULATION_TRIGGER_NUM_OF_MOTORS = 1;
        public static final double TRIGGER_SIMULATION_ROTATION_MULTIPLIER = 0.5;
        public static final double TRIGGER_X_POSITION = 3;
        public static final double TRIGGER_Y_POSITION = 1;

        public static final LoggedMechanismRoot2d TRIGGER_ROOT = ROBOT_MECHANISM.getRoot(SUBSYSTEM, TRIGGER_X_POSITION, TRIGGER_Y_POSITION);

        public static final Color8Bit TRIGGER_LIGAMENT_COLOR = new Color8Bit(Color.kBlue);
    }
}
