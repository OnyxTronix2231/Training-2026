package frc.robot.visualization;

import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.trigger.Trigger;

import static frc.robot.subsystems.trigger.TriggerConstants.*;
import static frc.robot.subsystems.trigger.TriggerConstants.TriggerSimulationConstants.*;

public class TriggerVisualization extends VisualizedSubsystem {

    private final Trigger trigger;
    private final VisualizationLib.Wheel wheel;

    public TriggerVisualization() {
        trigger = Trigger.getInstance();
        wheel = new VisualizationLib.Wheel(TRIGGER_ROOT, TRIGGER_SUBSYSTEM_NAME, TRIGGER_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        wheel.spin(trigger.getRPS() * SIMULATION_ROTATION_MULTIPLIER);
    }
}
