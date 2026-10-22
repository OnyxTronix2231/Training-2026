package frc.robot.subsystems.answers.L5;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class TriggerConstants {
    public static final String TRIGGER_SUBSYSTEM_NAME = "Trigger";

    public static final String TRIGGER_MOTOR_NAME = "TriggerMotor";

    public static class TriggerSimulationConstants {
        public static final int SIMULATION_TRIGGER_MOTOR_ID = 11;

        public static final int SIMULATION_TRIGGER_NUM_OF_MOTORS = 1;

        public static final double SIMULATION_TRIGGER_LENGTH_METERS = 0.001;
        public static final double SIMULATION_TRIGGER_MASS_KG = 0.001;

        public static final double TRIGGER_VISUALIZATION_MULTIPLIER = 0.5;

        public static final double TRIGGER_X_POSITION = 3.17;
        public static final double TRIGGER_Y_POSITION = 0.68;
        public static final LoggedMechanismRoot2d TRIGGER_ROOT = ROBOT_MECHANISM.getRoot(TRIGGER_SUBSYSTEM_NAME, TRIGGER_X_POSITION, TRIGGER_Y_POSITION);

        public static final Color8Bit TRIGGER_LIGAMENT_COLOR = new Color8Bit(Color.kYellow);
    }

    public static class TriggerRobotConstants {
        public static final int ROBOT_TRIGGER_MOTOR_ID = 18;
    }
}
