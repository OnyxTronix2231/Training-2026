package frc.robot.visualization;

import frc.robot.subsystems.Hinge.HingeIOSimulation;

import static frc.robot.subsystems.Hinge.HingeConstants.ShooterSimulationConstants.*;

public class HingeVisualization extends VisualizedSubsystem {

    private final HingeVisualization hingeVisualization;

    public HingeVisualization(HingeVisualization hingeVisualization) {
        this.hingeVisualization = hingeVisualization;
    }

    @Override
    protected void updateVisualization() {
    }
}
