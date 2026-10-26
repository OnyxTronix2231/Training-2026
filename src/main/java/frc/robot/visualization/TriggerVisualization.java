package frc.robot.visualization;

import frc.robot.lib.VisualizationLib.Wheel;
import frc.robot.subsystems.Trigger.Trigger;

import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerSimulationConstants.*;
import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;

public class TriggerVisualization extends VisualizedSubsystem{
    private final Trigger trigger;
    private final Wheel wheel;

    public TriggerVisualization() {
        this.trigger = Trigger.getInstance();

        wheel = new Wheel(TRIGGER_ROOT, TRIGGER_SUBSYSTEM_NAME, TRIGGER_LIGAMENT_COLOR);
    }
    @Override
    public void updateVisualization() {
        wheel.spin(trigger.getRPS()*SIMULATION_ROTATION_MULTIPLIER);
    }
}
