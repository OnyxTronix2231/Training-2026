package frc.robot.visualization.answers;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.answers.L5.Trigger;
import frc.robot.visualization.VisualizedSubsystem;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.lib.VisualizationLib.generateVisualizedWheel;
import static frc.robot.lib.VisualizationLib.updateVisualizedWheel;
import static frc.robot.subsystems.answers.L5.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.answers.L5.TriggerConstants.TriggerSimulationConstants.TRIGGER_VISUALIZATION_MULTIPLIER;

public class TriggerVisualization extends VisualizedSubsystem {
    private Trigger trigger;
    
    public TriggerVisualization() {
        trigger = Trigger.getInstance();
        
        generateVisualizedWheel(
            TriggerVisualization.TriggerVisualizationMechanism.TRIGGER, TriggerVisualization.TriggerVisualizationMechanism.TRIGGER_WHEEL_PART_COUNT,
            TriggerVisualization.TriggerVisualizationMechanism.triggerRoot, TRIGGER_SUBSYSTEM_NAME, TriggerVisualization.TriggerVisualizationMechanism.TRIGGER_LIGAMENT_COLOR
        );
    }

    @Override
    protected void updateVisualization() {
        updateVisualizedWheel(TriggerVisualization.TriggerVisualizationMechanism.TRIGGER,
            trigger.getRPS(), TRIGGER_VISUALIZATION_MULTIPLIER);
    }

    public class TriggerVisualizationMechanism {
        private static final double TRIGGER_X_POSITION = 3.17;
        private static final double TRIGGER_Y_POSITION = 0.68;
        public static final int TRIGGER_WHEEL_PART_COUNT = 4;
        public static final LoggedMechanismRoot2d triggerRoot = ROBOT_MECHANISM.getRoot(TRIGGER_SUBSYSTEM_NAME, TRIGGER_X_POSITION, TRIGGER_Y_POSITION);

        private static final Color8Bit TRIGGER_LIGAMENT_COLOR = new Color8Bit(Color.kYellow);

        public static final LoggedMechanismLigament2d[] TRIGGER = new LoggedMechanismLigament2d[4];
    }
}
