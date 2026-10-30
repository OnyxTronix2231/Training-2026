package frc.robot.visualization;

import frc.robot.subsystems.Hinge.Hinge;
import frc.robot.subsystems.Hinge.HingeIO;

import static frc.robot.subsystems.Hinge.HingeConstants.HingeSimulationConstants.HINGE_LIGAMENT;

public class HingeVisualization extends VisualizedSubsystem {

    private final Hinge hinge;

    public HingeVisualization() {
        hinge = Hinge.getInstance();
    }

    @Override
    protected void updateVisualization() {
        HINGE_LIGAMENT.setAngle(
hinge.getAngle()        );
    }
}
