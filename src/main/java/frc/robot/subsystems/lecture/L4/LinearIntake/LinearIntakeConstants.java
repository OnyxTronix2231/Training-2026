package frc.robot.subsystems.lecture.L4.LinearIntake;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import java.util.function.DoubleUnaryOperator;

import static frc.robot.visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class LinearIntakeConstants {
    public static final String LINEAR_INTAKE_SUBSYSTEM_NAME = "LinearIntake";
    public static final String LINEAR_INTAKE_MOTOR_NAME = "LinearIntakeMotor";

    public static final double LINEAR_INTAKE_GEAR_RATIO = 9;
    public static final double LINEAR_INTAKE_GEAR_CIRCUMFERENCE_METERS = 0.1;

    public static final DoubleUnaryOperator ROTATIONS_TO_METERS = rotations -> (rotations / LINEAR_INTAKE_GEAR_RATIO) * LINEAR_INTAKE_GEAR_CIRCUMFERENCE_METERS;

    public static class LinearIntakeSimulationConstants {
        public static final int SIMULATION_LINEAR_INTAKE_MOTOR_ID = 41;

        public static final int SIMULATION_LINEAR_INTAKE_NUM_OF_MOTORS = 1;

        public static final double SIMULATION_LINEAR_INTAKE_LENGTH_METERS = 0.001;
        public static final double SIMULATION_LINEAR_INTAKE_MASS_KG = 0.001;

        private static final double LINEAR_INTAKE_X_POSITION = 3;
        private static final double LINEAR_INTAKE_Y_POSITION = 0.2;
        public static final LoggedMechanismRoot2d LINEAR_INTAKE_ROOT = ROBOT_MECHANISM.getRoot(
                LINEAR_INTAKE_SUBSYSTEM_NAME, LINEAR_INTAKE_X_POSITION, LINEAR_INTAKE_Y_POSITION);

        // Keeps the bar visible when the intake is fully closed
        public static final double MIN_LENGTH = 0.2;
        private static final double LINEAR_INTAKE_LIGAMENT_ANGLE = 180;
        private static final double LINEAR_INTAKE_LIGAMENT_LINE_WIDTH = 5;
        public static final Color8Bit COLOR = new Color8Bit(Color.kOrange);
        public static final LoggedMechanismLigament2d LINEAR_INTAKE_LIGAMENT = LINEAR_INTAKE_ROOT.append(
                new LoggedMechanismLigament2d(LINEAR_INTAKE_SUBSYSTEM_NAME,
                        MIN_LENGTH, LINEAR_INTAKE_LIGAMENT_ANGLE,
                        LINEAR_INTAKE_LIGAMENT_LINE_WIDTH, COLOR
                )
        );
    }
}
