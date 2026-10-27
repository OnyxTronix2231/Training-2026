package frc.robot.visualization;

import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import frc.robot.subsystems.Hinge.Hinge;
import frc.robot.subsystems.Hinge.HingeIO;

import static frc.robot.subsystems.Hinge.HingeConstants.Hinge_Simulation_Constants.*;
import static frc.robot.subsystems.Hinge.HingeConstants.HINGE_SUBSYSTEM_NAME;

public class HingeVisualization extends VisualizedSubsystem{
    private final Hinge hinge;

    public HingeVisualization() {
        hinge = Hinge.getInstance();
    }

    @Override
    protected void updateVisualization() {
        HINGE.setAngle(hinge.getAngle());
    }
}
