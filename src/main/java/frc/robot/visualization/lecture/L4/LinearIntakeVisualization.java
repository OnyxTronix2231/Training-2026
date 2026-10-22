package frc.robot.visualization.lecture.L4;

import frc.robot.subsystems.lecture.L4.LinearIntake.LinearIntakeIO;
import frc.robot.visualization.VisualizedSubsystem;

import static frc.robot.subsystems.lecture.L4.LinearIntake.LinearIntakeConstants.LinearIntakeSimulationConstants.LINEAR_INTAKE_LIGAMENT;
import static frc.robot.subsystems.lecture.L4.LinearIntake.LinearIntakeConstants.LinearIntakeSimulationConstants.MIN_LENGTH;

public class LinearIntakeVisualization extends VisualizedSubsystem {
    private final LinearIntakeIO linearIntake;

    public LinearIntakeVisualization(LinearIntakeIO linearIntake) {
        this.linearIntake = linearIntake;
    }

    @Override
    protected void updateVisualization() {
        LINEAR_INTAKE_LIGAMENT.setLength(linearIntake.getPosition() + MIN_LENGTH);
    }
}
