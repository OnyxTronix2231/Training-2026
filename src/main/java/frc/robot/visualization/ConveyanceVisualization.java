package frc.robot.visualization;

import frc.robot.lib.VisualizationLib.*;
import frc.robot.subsystems.conveyance.ConveyanceIOSImulation;

import static frc.robot.subsystems.conveyance.ConveyanceConstants.*;
import static frc.robot.subsystems.conveyance.ConveyanceConstants.ShooterSimulationConstants.*;

public class ConveyanceVisualization extends VisualizedSubsystem {

    private final ConveyanceIOSImulation conveyanceIOSImulation;
    private final Wheel wheel;

    public ConveyanceVisualization(ConveyanceIOSImulation conveyanceIOSImulation) {
        this.conveyanceIOSImulation = conveyanceIOSImulation;

        wheel = new Wheel(CONVEYANCE_ROOT, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        wheel.spin(conveyanceIOSImulation.getRPS() * SIMULATION_ROTATION_MULTIPLIER);
    }
}
