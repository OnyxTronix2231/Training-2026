package frc.robot.subsystems.answers.L4.Turret;

import java.util.function.DoubleUnaryOperator;

public class TurretConstants {
    public static final int TURRET_MOTOR_ID = 23;

    public static final int TURRET_ENCODER_ID = 24;

    public static final double TURRET_CONVERSION_RATE = 44.0/1.0;

    public static final String TURRET_SUBSYSTEM_NAME = "Turret";
    public static final String TURRET_MOTOR_NAME = "TurretMotor";

    public static final DoubleUnaryOperator ROTATIONS_TO_ANGLE = rotations -> rotations * 360;

    public static final double TURRET_FORWARD_SOFT_LIMIT_DEGREES= 360;
    public static final double TURRET_REVERSE_SOFT_LIMIT_DEGREES= -360;

    public static final double TURRET_CANCODER_OFFSET = 0;

    public static final int SIMULATION_TURRET_NUM_OF_MOTORS = 1;

    public static final double SIMULATION_TURRET_LENGTH_METERS = 0.001;
    public static final double SIMULATION_TURRET_MASS_KG = 0.001;

    public static final double SIMULATION_TURRET_CONVERSION_RATE = 1;

    public static final double SIMULATION_TURRET_FORWARD_SOFT_LIMIT_DEGREES= 360;
    public static final double SIMULATION_TURRET_REVERSE_SOFT_LIMIT_DEGREES= -360;
}
