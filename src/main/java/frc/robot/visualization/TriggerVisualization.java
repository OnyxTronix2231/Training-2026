package frc.robot.visualization;

import frc.robot.lib.VisualizationLib.Wheel;
import frc.robot.subsystems.Trigger.Trigger;
import frc.robot.subsystems.Trigger.TriggerIO;
import frc.robot.subsystems.Trigger.TriggerIOSimulation;

import static frc.robot.subsystems.Trigger.TriggerConstants.TRIGGER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerSimulationConstants.*;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.SHOOTER_SUBSYSTEM_NAME;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.ShooterSimulationConstants.SHOOTER_ROOT;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.ShooterSimulationConstants.SIMULATION_ROTATION_MULTIPLIER;

public class TriggerVisualization extends VisualizedSubsystem {
    private final Trigger trigger;
    private final Wheel wheel;

    public TriggerVisualization(){
        trigger = Trigger.getInstance();

       wheel = new Wheel(TRIGGER_ROOT,TRIGGER_SUBSYSTEM_NAME,TRIGGER_LIGAMENT_COLOR);

    }

    @Override
    protected void updateVisualization() {
        wheel.spin(trigger.getRPS()*SIMULATION_TRIGGER_MULTIPLIER);
    }
}
