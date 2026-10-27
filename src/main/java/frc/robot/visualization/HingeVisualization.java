package frc.robot.visualization;

import edu.wpi.first.units.measure.Angle;
import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.Hinge.Hinge;

import static frc.robot.subsystems.Hinge.HingeConstants.*;
import static frc.robot.subsystems.Hinge.HingeConstants.HingeSimulationConstants.HINGE;
import static frc.robot.subsystems.Trigger.TriggerConstants.TriggerSimulationConstants.*;

public class HingeVisualization extends VisualizedSubsystem {

    private Hinge hinge;

    public HingeVisualization() {
        hinge = Hinge.getInstance();

    }

    @Override
    protected void updateVisualization() {
        HINGE.setAngle(hinge.getAngle());
    }
}
