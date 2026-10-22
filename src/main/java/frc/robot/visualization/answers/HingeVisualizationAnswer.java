package frc.robot.visualization.answers;

import frc.robot.subsystems.answers.L4.Hinge.HingeIOSimulation;
import frc.robot.visualization.VisualizedSubsystem;

import static frc.robot.subsystems.answers.L4.Hinge.HingeConstants.HingeSimulationConstants.HINGE;

public class HingeVisualizationAnswer extends VisualizedSubsystem {
    HingeIOSimulation hingeIOSimulation;

    public HingeVisualizationAnswer(HingeIOSimulation hingeIOSimulation) {
        this.hingeIOSimulation = hingeIOSimulation;
    }

    @Override
    protected void updateVisualization() {
        HINGE.setAngle(hingeIOSimulation.getAngle());
    }
}
