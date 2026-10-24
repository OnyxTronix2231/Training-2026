package frc.robot.visualization;

import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.Conveyance.ConveyanceIO;
import frc.robot.subsystems.lecture.shooter.ShooterIO;

import static frc.robot.subsystems.Conveyance.ConveyanceConstants.CONVEYANCE_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;

public class ConveyanceVisualization extends VisualizedSubsystem {

    private final ConveyanceIO conveyanceIO;
    private final VisualizationLib.Wheel wheel;

    public ConveyanceVisualization(ConveyanceIO conveyanceIO) {
        this.conveyanceIO = conveyanceIO;

        wheel = new VisualizationLib.Wheel(CONVEYANCE_ROOT, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        wheel.spin(conveyanceIO.getRPS() * SIMULATION_CONVEYANCE_ROTATION_MULTIPLIER);
    }
}


