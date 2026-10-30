package frc.robot.visualization;

import frc.robot.lib.VisualizationLib.Wheel;
import frc.robot.subsystems.Conveyance.Conveyance;
import frc.robot.subsystems.Conveyance.ConveyanceIO;

import static frc.robot.subsystems.Conveyance.ConveyanceConstants.CONVEYANCE_NAME;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;

public class ConveyanceVisualization extends VisualizedSubsystem {
    private final Conveyance conveyance;
    private final Wheel wheel;

    public ConveyanceVisualization() {

conveyance = Conveyance.getInstance();
        wheel = new Wheel(CONVEYANCE_ROOT, CONVEYANCE_NAME, CONVEYANCE_LIGAMENT_COLOR);

    }

    @Override
    protected void updateVisualization() {
        wheel.spin(conveyance.getRPS()* SIMULATION_ROTATION_MULTIPLIER);
    }
}
