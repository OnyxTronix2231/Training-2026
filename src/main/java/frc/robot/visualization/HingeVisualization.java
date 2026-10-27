package frc.robot.visualization;

import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.HingeIO.Hinge;

import static frc.robot.subsystems.ConveyanceIO.ConveyanceConstants.SUBSYSTEM;
import static frc.robot.subsystems.HingeIO.HingeConstants.HingeSimulationConstants.*;

public class HingeVisualization extends VisualizedSubsystem {
    private final Hinge hinge;
    private final VisualizationLib.Wheel wheel;

    public HingeVisualization() {
        this.hinge=Hinge.getInstance();


        wheel = new VisualizationLib.Wheel(HINGE_ROOT,SUBSYSTEM, HINGE_LIGAMENT_COLOR);
    }


    protected void updateVisualization() {
       HINGE.setAngle(hinge.getAngle());
    }
}
