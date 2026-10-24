package frc.robot.visualization;

import frc.robot.lib.VisualizationLib.Wheel;
import frc.robot.subsystems.Conveyance.ConveyanceIO;

import static frc.robot.subsystems.Conveyance.ConveyanceConstants.CONVEYANCE_NAME;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;

public class ConveyanceVisualization extends VisualizedSubsystem {
    private final ConveyanceIO conveyanceIO;
    private final Wheel wheel;

    public ConveyanceVisualization(ConveyanceIO conveyanceIO) {

        this.conveyanceIO = conveyanceIO;

        wheel = new Wheel(CONVEYANCE_ROOT, CONVEYANCE_NAME, CONVEYANCE_LIGAMENT_COLOR);

    }

    @Override
    protected void updateVisualization() {
        wheel.spin(conveyanceIO.getRPS() * SIMULATION_ROTATION_MULTIPLIER);
    }
}
