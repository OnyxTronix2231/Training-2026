package frc.robot.visualization.answers;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.answers.L4.Conveyance.ConveyanceIOSimulation;
import frc.robot.visualization.VisualizedSubsystem;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.lib.VisualizationLib.*;
import static frc.robot.subsystems.answers.L4.Conveyance.ConveyanceConstants.CONVEYANCE_SUBSYSTEM_NAME;
import static frc.robot.subsystems.answers.L4.Conveyance.ConveyanceConstants.ConveyanceConstantsSimulation.CONVEYANCE_VISUALIZATION_MULTIPLIER;

public class ConveyanceVisualization extends VisualizedSubsystem {
    ConveyanceIOSimulation conveyanceIOSimulation;
    public ConveyanceVisualization(ConveyanceIOSimulation conveyanceIOSimulation) {
        this.conveyanceIOSimulation = conveyanceIOSimulation;

        generateVisualizedWheel(
            ConveyanceVisualizationMechanism.CONVEYANCE, ConveyanceVisualizationMechanism.CONVEYANCE_WHEEL_PART_COUNT,
            ConveyanceVisualizationMechanism.conveyanceRoot, CONVEYANCE_SUBSYSTEM_NAME, ConveyanceVisualizationMechanism.CONVEYANCE_LIGAMENT_COLOR
        );
    }

    @Override
    protected void updateVisualization() {
        updateVisualizedWheel(ConveyanceVisualizationMechanism.CONVEYANCE,
            conveyanceIOSimulation.getMotorInputs().getMotorAngularVelocityRotPerSec(), CONVEYANCE_VISUALIZATION_MULTIPLIER);
    }

    public class ConveyanceVisualizationMechanism {
        private static final double CONVEYANCE_X_POSITION = 3.17;
        private static final double CONVEYANCE_Y_POSITION = 0.68;
        public static final int CONVEYANCE_WHEEL_PART_COUNT = 4;
        public static final LoggedMechanismRoot2d conveyanceRoot = ROBOT_MECHANISM.getRoot(CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_X_POSITION, CONVEYANCE_Y_POSITION);

        private static final Color8Bit CONVEYANCE_LIGAMENT_COLOR = new Color8Bit(Color.kYellow);

        public static final LoggedMechanismLigament2d[] CONVEYANCE = new LoggedMechanismLigament2d[4];
    }
}
