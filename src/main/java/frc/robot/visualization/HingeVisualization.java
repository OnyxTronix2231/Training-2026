package frc.robot.visualization;

import frc.robot.subsystems.Hinge.HingeConstants;
import frc.robot.subsystems.Hinge.HingeIO;

import static frc.robot.subsystems.Hinge.HingeConstants.HingeSimulationConstants.HINGE_LIGAMENT;

public class HingeVisualization extends VisualizedSubsystem{
    private final HingeIO hingeIO;
public HingeVisualization(HingeIO hingeIO){
    this.hingeIO = hingeIO;
}

    @Override
    protected void updateVisualization() {
        HINGE_LIGAMENT.setAngle(
                hingeIO.getAngle()
        );
    }
}
