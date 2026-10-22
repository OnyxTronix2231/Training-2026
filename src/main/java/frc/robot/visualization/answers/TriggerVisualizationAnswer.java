package frc.robot.visualization.answers;

import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.answers.L6.Trigger;
import frc.robot.visualization.VisualizedSubsystem;

import static frc.robot.subsystems.answers.L6.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.answers.L6.TriggerConstants.TriggerSimulationConstants.*;

public class TriggerVisualizationAnswer extends VisualizedSubsystem {
    private final Trigger trigger;
    private final VisualizationLib.Wheel wheel;
    
    public TriggerVisualizationAnswer() {
        trigger = Trigger.getInstance();
        wheel = new VisualizationLib.Wheel(TRIGGER_ROOT, TRIGGER_SUBSYSTEM_NAME, TRIGGER_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        wheel.spin(trigger.getRPS() * TRIGGER_VISUALIZATION_MULTIPLIER);
    }
}
