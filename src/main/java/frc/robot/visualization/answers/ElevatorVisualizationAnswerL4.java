package frc.robot.visualization.answers;

import frc.robot.subsystems.answers.L4.Elevator.ElevatorIOSimulation;
import frc.robot.visualization.VisualizedSubsystem;

import static frc.robot.subsystems.answers.L4.Elevator.ElevatorConstants.ElevatorSimulationConstants.ELEVATOR;

public class ElevatorVisualizationAnswerL4 extends VisualizedSubsystem {
    private final ElevatorIOSimulation elevatorIOSimulation;

    public ElevatorVisualizationAnswerL4(ElevatorIOSimulation elevatorIOSimulation) {
        this.elevatorIOSimulation = elevatorIOSimulation;
    }

    @Override
    protected void updateVisualization() {
        ELEVATOR.setLength(0.3 + elevatorIOSimulation.getLength());
    }
}