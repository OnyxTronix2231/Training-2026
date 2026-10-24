package frc.robot.visualization;

import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.Conveyance.ConveyanceIO;
import frc.robot.subsystems.Hinge.HingeIO;

public class HingeVisualization extends VisualizedSubsystem{
    private final HingeIO hingeIO;

    public HingeVisualization(HingeIO hingeIO) {
        this.hingeIO = hingeIO;


    }

    @Override
    protected void updateVisualization() {

    }
}
