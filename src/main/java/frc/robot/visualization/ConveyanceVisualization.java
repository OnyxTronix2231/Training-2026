package frc.robot.visualization;

import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.conveyance.ConveyanceIOSimulation;

import static frc.robot.subsystems.conveyance.ConveyanceConstants.CONVEYANCE_SUBSYSTEM_NAME;
import static frc.robot.subsystems.conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;

public class ConveyanceVisualization extends VisualizedSubsystem {

    private final ConveyanceIOSimulation conveyanceIOSimulation;
    private final VisualizationLib.Wheel wheel;

    public ConveyanceVisualization(ConveyanceIOSimulation conveyanceIOSimulation) {
        this.conveyanceIOSimulation = conveyanceIOSimulation;
        wheel = new VisualizationLib.Wheel(CONVEYANCE_ROOT, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        wheel.spin(conveyanceIOSimulation.getRPS() * SIMULATION_ROTATION_MULTIPLIER);
    }
}
