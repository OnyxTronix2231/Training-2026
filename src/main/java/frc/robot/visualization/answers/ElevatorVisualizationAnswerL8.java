package frc.robot.visualization.answers;

import frc.robot.subsystems.answers.L8.Elevator;
import frc.robot.visualization.VisualizedSubsystem;

import static frc.robot.subsystems.answers.L8.ElevatorConstants.ElevatorSimulationConstants.ELEVATOR;

public class ElevatorVisualizationAnswerL8 extends VisualizedSubsystem {
    private final Elevator elevator;

    public ElevatorVisualizationAnswerL8() {
        this.elevator = Elevator.getInstance();
    }

    @Override
    protected void updateVisualization() {
        ELEVATOR.setLength(0.3 + elevator.getElevatorPosition());
    }
}