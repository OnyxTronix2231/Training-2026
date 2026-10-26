package frc.robot.visualization;

import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.conveyance.ConveyanceIOSimulation;
import frc.robot.subsystems.trigger.Trigger;
import frc.robot.subsystems.trigger.TriggerIOSimulation;

import static frc.robot.subsystems.conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;
import static frc.robot.subsystems.trigger.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.trigger.TriggerConstants.TriggerSimulationConstants.TRIGGER_LIGAMENT_COLOR;
import static frc.robot.subsystems.trigger.TriggerConstants.TriggerSimulationConstants.TRIGGER_ROOT;

public class TriggerVisualization extends VisualizedSubsystem {

    private final Trigger trigger;
    private final VisualizationLib.Wheel wheel;

    public TriggerVisualization() {
        this.trigger = Trigger.getInstance();
        wheel = new VisualizationLib.Wheel(TRIGGER_ROOT, TRIGGER_SUBSYSTEM_NAME, TRIGGER_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        wheel.spin(trigger.getRPS() * SIMULATION_ROTATION_MULTIPLIER);
    }
}
