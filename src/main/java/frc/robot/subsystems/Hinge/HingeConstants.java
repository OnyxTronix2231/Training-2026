package frc.robot.subsystems.Hinge;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;

public class HingeConstants {
    public static final String HINGE_SUBSYSTEM_NAME = "Hinge";

    public static class Hinge_RobotAConstants {
        public static final int HINGE_MOTOR_ID = 5;
        public static final String HINGE_MOTOR_NAME = "HingeMotor";
    }
    public static class Hinge_Simulation_Constants {
        private static final double Hinge_X_Position = 3.4;
        private static final double Hinge_Y_Position = 1;

        static final Color8Bit HINGE_LIGAMENT_COLOR = new Color8Bit(Color.kDarkCyan);
    }
}
