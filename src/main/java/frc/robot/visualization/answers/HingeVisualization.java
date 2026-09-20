package frc.robot.visualization.answers;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.answers.L4.Hinge.HingeIOSimulation;
import frc.robot.visualization.VisualizedSubsystem;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.subsystems.answers.L4.Hinge.HingeConstants.HINGE_SUBSYSTEM_NAME;

public class HingeVisualization extends VisualizedSubsystem {
    HingeIOSimulation hingeIOSimulation;
    public HingeVisualization(HingeIOSimulation hingeIOSimulation) {
        this.hingeIOSimulation = hingeIOSimulation;
    }

    @Override
    protected void updateVisualization() {
        System.out.println(hingeIOSimulation.getMotorInputs().getMotorValue().getAsDouble());
        HingeVisualizationMechanism.HINGE.setAngle(hingeIOSimulation.getMotorInputs().getMotorValue().getAsDouble());
    }

    public class HingeVisualizationMechanism {
        private static final double HINGE_X_POSITION = 3.17;
        private static final double HINGE_Y_POSITION = 0.68;
        public static final double HINGE_LIGAMENT_LENGTH = 0.3;
        public static final double HINGE_LIGAMENT_ANGLE = 0;
        public static final int HINGE_LIGAMENT_LINE_WIDTH = 4;
        public static final LoggedMechanismRoot2d hingeRoot = ROBOT_MECHANISM.getRoot(HINGE_SUBSYSTEM_NAME, HINGE_X_POSITION, HINGE_Y_POSITION);

        private static final Color8Bit HINGE_LIGAMENT_COLOR = new Color8Bit(Color.kBlue);

        public static final LoggedMechanismLigament2d HINGE = hingeRoot.append(
            new LoggedMechanismLigament2d(
                "HINGE",
                HINGE_LIGAMENT_LENGTH, HINGE_LIGAMENT_ANGLE,
                HINGE_LIGAMENT_LINE_WIDTH, HINGE_LIGAMENT_COLOR
            )
        );
    }
}
