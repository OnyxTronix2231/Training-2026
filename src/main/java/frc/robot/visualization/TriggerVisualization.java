package frc.robot.visualization;

import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.Trigger.Trigger;
import frc.robot.subsystems.Trigger.TriggerIO;
import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerSimulationConstants.*;

public class TriggerVisualization extends VisualizedSubsystem {
    private final Trigger trigger;
    private final VisualizationLib.Wheel wheel;

    public TriggerVisualization(TriggerIO triggerIO) {
        trigger = Trigger.getInstance(trigger);

        wheel = new VisualizationLib.Wheel(TRIGGER_ROOT, TRIGGER_SUBSYSTEM_NAME, TRIGGER_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        wheel.spin(trigger.getRPS() * SIMULATION_TRIGGER_ROTATION_MULTIPLIER);
    }
}

}
