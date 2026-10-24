package frc.robot.visualization;

import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.Conveyance.ConveyanceIOSimulation;

import static frc.robot.subsystems.Conveyance.ConveyanceConstants.CONVEYANCE_VISUALIZATION_SUBSYSTEM_NAME;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceVisualizationConstants.CONVEYANCE_LIGAMENT_COLOR;
import static frc.robot.subsystems.Conveyance.ConveyanceConstants.ConveyanceVisualizationConstants.CONVEYANCE_ROOT;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.ShooterSimulationConstants.SIMULATION_ROTATION_MULTIPLIER;


public class ConveyanceVisualization extends VisualizedSubsystem {

    private ConveyanceIOSimulation conveyanceIOSimulation;
    private final VisualizationLib.Wheel wheel;


    public ConveyanceVisualization(ConveyanceIOSimulation conveyanceIOSimulation) {
        this.conveyanceIOSimulation = conveyanceIOSimulation;
        wheel = new VisualizationLib.Wheel(CONVEYANCE_ROOT, CONVEYANCE_VISUALIZATION_SUBSYSTEM_NAME, CONVEYANCE_LIGAMENT_COLOR);


    }

    @Override
    protected void updateVisualization() {
        wheel.spin(conveyanceIOSimulation.getRPS() * SIMULATION_ROTATION_MULTIPLIER);
    }

}
