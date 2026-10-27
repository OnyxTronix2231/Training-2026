package frc.robot.visualization;

import frc.robot.subsystems.Hinge.Hinge;

import static frc.robot.subsystems.Hinge.HingeConstants.HingeSimulationConstants.HINGE;

public class HingeVisualization extends VisualizedSubsystem {

    private final Hinge hinge;

    public HingeVisualization() {
        hinge = Hinge.getInstance();
    }

    @Override
    protected void updateVisualization() {
        HINGE.setAngle(hinge.getAngle());
    }
}
