package frc.robot.visualization;

import frc.robot.lib.VisualizationLib.*;
import frc.robot.subsystems.conveyance.ConveyanceIOSimulation;

import static frc.robot.subsystems.conveyance.ConveyanceConstants.*;
import static frc.robot.subsystems.conveyance.ConveyanceConstants.ShooterSimulationConstants.*;

public class ConveyanceVisualization extends VisualizedSubsystem {

    private final ConveyanceIOSimulation conveyanceIOSImulation;
    private final Wheel wheel;

    public ConveyanceVisualization(ConveyanceIOSimulation conveyanceIOSImulation) {
        this.conveyanceIOSImulation = conveyanceIOSImulation;

        wheel = new Wheel(CONVEYANCE_ROOT, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        wheel.spin(conveyanceIOSImulation.getRPS() * SIMULATION_ROTATION_MULTIPLIER);
    }
}
