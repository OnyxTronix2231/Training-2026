package frc.robot.visualization;

import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.Trigger.Trigger;
import frc.robot.subsystems.Trigger.TriggerIOSimulation;

import static frc.robot.subsystems.Conveyance.ConveyanceConstants.CONVEYANCE_VISUALIZATION_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceVisualizationConstants.CONVEYANCE_LIGAMENT_COLOR;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceVisualizationConstants.CONVEYANCE_ROOT;
import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_VISUALIZATION_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerVisualizationConstants.TRIGGER_LIGAMENT_COLOR;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerVisualizationConstants.TRIGGER_ROOT;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.ShooterSimulationConstants.SIMULATION_ROTATION_MULTIPLIER;

public class TriggerVisualization extends VisualizedSubsystem {
    private final Trigger trigger;
    private final VisualizationLib.Wheel wheel;

    public TriggerVisualization(){
        trigger = Trigger.getInstance();

        wheel = new VisualizationLib.Wheel(TRIGGER_ROOT, TRIGGER_VISUALIZATION_SUBSYSTEM_NAME, TRIGGER_LIGAMENT_COLOR);


    }

    @Override
    protected void updateVisualization() {
        wheel.spin(trigger.getRPS() * SIMULATION_ROTATION_MULTIPLIER);

    }
}
