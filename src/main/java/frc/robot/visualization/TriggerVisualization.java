package frc.robot.visualization;

import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.Trigger.Trigger;

import static frc.robot.subsystems.ConveyanceIO.ConveyanceConstants.SUBSYSTEM;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerSimulationConstants.*;

public class TriggerVisualization extends VisualizedSubsystem{
    private final Trigger trigger;
    private final VisualizationLib.Wheel wheel;

    public TriggerVisualization() {
        this.trigger=Trigger.getInstance();


        wheel = new VisualizationLib.Wheel(TRIGGER_ROOT,SUBSYSTEM, TRIGGER_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        wheel.spin(trigger.getRPS() * TRIGGER_SIMULATION_ROTATION_MULTIPLIER);
    }
}




