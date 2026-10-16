package frc.robot.subsystems.lecture.L4.Turret;

import edu.wpi.first.math.util.Units;

import java.util.function.DoubleUnaryOperator;

public class TurretConstants {
    public static final String TURRET_SUBSYSTEM_NAME = "Turret";
    public static final String TURRET_MOTOR_NAME = "TurretMotor";

    public static final double TURRET_GEAR_RATIO = 10;

    public static final DoubleUnaryOperator ROTATIONS_TO_DEGREES = rotations -> Units.rotationsToDegrees(rotations / TURRET_GEAR_RATIO);

    public static class TurretSimulationConstants {
        public static final int SIMULATION_TURRET_MOTOR_ID = 40;

        public static final int SIMULATION_TURRET_NUM_OF_MOTORS = 1;

        public static final double SIMULATION_TURRET_LENGTH_METERS = 0.001;
        public static final double SIMULATION_TURRET_MASS_KG = 0.001;
    }
}
