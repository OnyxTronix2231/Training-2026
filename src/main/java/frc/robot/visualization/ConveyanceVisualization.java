package frc.robot.visualization;

import frc.robot.lib.VisualizationLib;
import frc.robot.subsystems.ConveyanceIO.ConveyanceIO;


import static frc.robot.subsystems.ConveyanceIO.ConveyanceConstants.ConveyanceSimulationConstants.*;
import static frc.robot.subsystems.ConveyanceIO.ConveyanceConstants.SUBSYSTEM;
import static frc.robot.subsystems.lecture.shooter.ShooterConstants.ShooterSimulationConstants.*;

public class ConveyanceVisualization extends VisualizedSubsystem{

    private final ConveyanceIO ConveyanceIO;
    private final VisualizationLib.Wheel wheel;

    public ConveyanceVisualization(ConveyanceIO ConveyanceIO) {
        this.ConveyanceIO =  ConveyanceIO;

        wheel = new VisualizationLib.Wheel(CONVEYANCE_ROOT,SUBSYSTEM, CONVEYANCE_LIGAMENT_COLOR);
    }

    @Override
    protected void updateVisualization() {
        wheel.spin(ConveyanceIO.getRPS() * CONVEYANCE_SIMULATION_ROTATION_MULTIPLIER);
    }
}


