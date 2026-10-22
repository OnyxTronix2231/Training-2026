package frc.robot.subsystems.lecture.L4.Shooter;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class ShooterConstants {
    public static final String SHOOTER_SUBSYSTEM_NAME = "Shooter";
    public static final String SHOOTER_MASTER_MOTOR_NAME = "ShooterMasterMotor";

    public static class ShooterRobotConstants {
        public static final int ROBOT_SHOOTER_MOTOR_ID = 13;

        public static final double ROBOT_SHOOTER_CONVERSION_RATE = 36 / 24.0;
    }

    public static class ShooterSimulationConstants {
        public static final int SIMULATION_SHOOTER_MASTER_MOTOR_ID = 39;

        public static final int SIMULATION_SHOOTER_NUM_OF_MOTORS = 1;

        public static final double SIMULATION_SHOOTER_LENGTH_METERS = 0.001;
        public static final double SIMULATION_SHOOTER_MASS_KG = 0.001;

        public static final double SIMULATION_ROTATION_MULTIPLIER = 0.5;

        public static final double SHOOTER_X_POSITION = 3.4;
        public static final double SHOOTER_Y_POSITION = 1;
        public static final LoggedMechanismRoot2d SHOOTER_ROOT = ROBOT_MECHANISM.getRoot(
                SHOOTER_SUBSYSTEM_NAME, SHOOTER_X_POSITION, SHOOTER_Y_POSITION);

        public static final Color8Bit SHOOTER_LIGAMENT_COLOR = new Color8Bit(Color.kDarkRed);
    }
}
