package frc.robot.visualization;

import frc.robot.lib.VisualizationLib.*;
import frc.robot.subsystems.conveyance.Conveyance;

import static frc.robot.subsystems.conveyance.ConveyanceConstants.*;
import static frc.robot.subsystems.conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;

public class ConveyanceVisualization extends VisualizedSubsystem {

    private final Conveyance conveyance;
    private final Wheel wheel;

    public ConveyanceVisualization() {
        conveyance = Conveyance.getInstance();

        wheel = new Wheel(CONVEYANCE_ROOT, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        wheel.spin(conveyance.getRPS() * SIMULATION_ROTATION_MULTIPLIER);
    }
}
