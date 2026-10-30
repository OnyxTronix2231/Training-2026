package frc.robot.subsystems.Trigger;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import java.nio.file.attribute.DosFileAttributes;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class TriggerConstants {

    public static final String TRIGGER_SUBSYSTEM_NAME = "Trigger";

    public static class TriggerSimulationConstants {
        public static final int TRIGGER_MOTOR_ID = 12;
        public static final String TRIGGER_MOTOR_NAME = "TriggerMotor";
        public static final String TRIGGER_CAN_BUS = "DriveTrain";
        public static final int SIMULATION_NUM_OF_MOTORS = 1;
        public static final double SIMULATION_TRIGGER_LENGTH_METERS = 0.001;
        public static final double SIMULATION_TRIGGER_MASS_KG = 0.001;
        public static final int SIMULATION_TRIGGER_GEARING = 1;
        private static final int TRIGGER_X_POSITION = 2;
        private static final double TRIGGER_Y_POSITION = 1;
        public static final LoggedMechanismRoot2d TRIGGER_ROOT = ROBOT_MECHANISM.getRoot(TRIGGER_SUBSYSTEM_NAME,
                TRIGGER_X_POSITION, TRIGGER_Y_POSITION);
        public static final Color8Bit TRIGGER_LIGAMENT_COLOR = new Color8Bit(Color.kBeige);
        public static final double SIMULATION_TRIGGER_MULTIPLIER =0.05;
        public static final double TRIGGER_DUTY_CYCLE_SPEED = 0.5;
    }
}
