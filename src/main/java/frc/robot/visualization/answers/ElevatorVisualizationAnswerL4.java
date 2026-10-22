package frc.robot.visualization.answers;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.answers.L4.Elevator.ElevatorIOSimulation;
import frc.robot.visualization.VisualizedSubsystem;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.subsystems.answers.L4.Elevator.ElevatorConstants.ELEVATOR_SUBSYSTEM_NAME;
import static frc.robot.subsystems.answers.L4.Elevator.ElevatorConstants.ElevatorSimulationConstants.ELEVATOR;

public class ElevatorVisualizationAnswerL4 extends VisualizedSubsystem {
    private final ElevatorIOSimulation elevatorIOSimulation;

    public ElevatorVisualizationAnswerL4(ElevatorIOSimulation elevatorIOSimulation) {
        this.elevatorIOSimulation = elevatorIOSimulation;
    }

    @Override
    protected void updateVisualization() {
        ELEVATOR.setLength(0.3 + elevatorIOSimulation.getMotorOutputs().getMotorValue().getAsDouble());
    }
}