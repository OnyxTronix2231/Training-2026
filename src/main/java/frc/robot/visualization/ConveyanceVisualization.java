package frc.robot.visualization;

import frc.robot.lib.VisualizationLib.Wheel;
import frc.robot.subsystems.Conveyance.ConveyanceIO;
import frc.robot.subsystems.Conveyance.ConveyanceIORobot;
import frc.robot.subsystems.Conveyance.ConveyanceIOSimulation;

import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.CONVEYANCE_SUBSYSTEM_NAME;

public class ConveyanceVisualization extends VisualizedSubsystem{

    private final ConveyanceIO conveyanceIO;
    private final Wheel wheel;

    public ConveyanceVisualization(ConveyanceIO conveyanceIO) {
        this.conveyanceIO = conveyanceIO;

        wheel = new Wheel(CONVEYANCE_ROOT, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_LIGAMENT_COLOR);
    }

    @Override
    public void updateVisualization() {
        wheel.spin(conveyanceIO.getRPS() * SIMULATION_ROTATION_MULTIPLIER);
    }
}
