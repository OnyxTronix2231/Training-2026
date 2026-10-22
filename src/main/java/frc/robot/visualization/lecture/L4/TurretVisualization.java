package frc.robot.visualization.lecture.L4;

import frc.robot.subsystems.lecture.L4.Turret.TurretIO;
import frc.robot.visualization.VisualizedSubsystem;

import static frc.robot.subsystems.lecture.L4.Turret.TurretConstants.TurretSimulationConstants.TURRET;

public class TurretVisualization extends VisualizedSubsystem {
    private final TurretIO turret;

    public TurretVisualization(TurretIO turret) {
        this.turret = turret;
    }

    @Override
    protected void updateVisualization() {
        TURRET.setAngle(turret.getAngle());
    }
}
