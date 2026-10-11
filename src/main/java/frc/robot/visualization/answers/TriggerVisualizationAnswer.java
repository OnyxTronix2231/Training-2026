package frc.robot.visualization.answers;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.answers.L6.Trigger;
import frc.robot.visualization.VisualizedSubsystem;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.subsystems.answers.L6.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.answers.L6.TriggerConstants.TriggerSimulationConstants.TRIGGER_VISUALIZATION_MULTIPLIER;

public class TriggerVisualizationAnswer extends VisualizedSubsystem {
    private Trigger trigger;
    private VisualizationLib.Wheel wheel;
    
    public TriggerVisualizationAnswer() {
        trigger = Trigger.getInstance();
        wheel = new VisualizationLib.Wheel(TriggerVisualizationMechanism.triggerRoot, TRIGGER_SUBSYSTEM_NAME, TriggerVisualizationMechanism.TRIGGER_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        wheel.spin(trigger.getRPS() * TRIGGER_VISUALIZATION_MULTIPLIER);
    }

    public class TriggerVisualizationMechanism {
        private static final double TRIGGER_X_POSITION = 3.17;
        private static final double TRIGGER_Y_POSITION = 0.68;
        public static final LoggedMechanismRoot2d triggerRoot = ROBOT_MECHANISM.getRoot(TRIGGER_SUBSYSTEM_NAME, TRIGGER_X_POSITION, TRIGGER_Y_POSITION);

        private static final Color8Bit TRIGGER_LIGAMENT_COLOR = new Color8Bit(Color.kYellow);
    }
}
