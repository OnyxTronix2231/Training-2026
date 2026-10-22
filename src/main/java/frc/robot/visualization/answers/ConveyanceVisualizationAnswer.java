package frc.robot.visualization.answers;

import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.answers.L4.Conveyance.ConveyanceIOSimulation;
import frc.robot.visualization.VisualizedSubsystem;

import static frc.robot.subsystems.answers.L4.Conveyance.ConveyanceConstants.CONVEYANCE_SUBSYSTEM_NAME;
import static frc.robot.subsystems.answers.L4.Conveyance.ConveyanceConstants.ConveyanceSimulationConstants.*;

public class ConveyanceVisualizationAnswer extends VisualizedSubsystem {
    ConveyanceIOSimulation conveyanceIOSimulation;
    VisualizationLib.Wheel wheel;

    public ConveyanceVisualizationAnswer(ConveyanceIOSimulation conveyanceIOSimulation) {
        this.conveyanceIOSimulation = conveyanceIOSimulation;

        wheel = new VisualizationLib.Wheel(CONVEYANCE_ROOT, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        wheel.spin(conveyanceIOSimulation.getRPS() * CONVEYANCE_VISUALIZATION_MULTIPLIER);
    }
}
