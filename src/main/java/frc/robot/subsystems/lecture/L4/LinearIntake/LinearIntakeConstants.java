package frc.robot.subsystems.lecture.L4.LinearIntake;

import java.util.function.DoubleUnaryOperator;

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
    }
}
