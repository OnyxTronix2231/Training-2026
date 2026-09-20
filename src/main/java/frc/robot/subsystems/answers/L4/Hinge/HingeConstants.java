package frc.robot.subsystems.answers.L4.Hinge;

import edu.wpi.first.math.util.Units;

import java.util.function.DoubleUnaryOperator;

public class HingeConstants {
    public static final int SIMULATION_HINGE_MOTOR_ID = 10;

    public static final int SIMULATION_HINGE_NUM_OF_MOTORS = 1;

    public static final double SIMULATION_HINGE_LENGTH_METERS = 0.001;
    public static final double SIMULATION_HINGE_MASS_KG = 0.001;

    public static final String HINGE_SUBSYSTEM_NAME = "Hinge";
    public static final String HINGE_MOTOR_NAME = "HingeMotor";

    public static final double SIMULATION_HINGE_FORWARD_SOFT_LIMIT_DEGREES = 180;
    public static final double SIMULATION_HINGE_REVERSE_SOFT_LIMIT_DEGREES = 0;

    public static final DoubleUnaryOperator ROTATIONS_TO_ANGLE = (rotations) -> Units.rotationsToDegrees(rotations);
}
