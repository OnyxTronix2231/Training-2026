package frc.robot.visualization.answers;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.answers.L4.Elevator.ElevatorIOSimulation;
import frc.robot.visualization.VisualizedSubsystem;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.subsystems.answers.L4.Elevator.ElevatorConstants.ELEVATOR_SUBSYSTEM_NAME;

public class ElevatorVisualization extends VisualizedSubsystem {
    private final ElevatorIOSimulation elevatorIOSimulation;

    public ElevatorVisualization(ElevatorIOSimulation elevatorIOSimulation) {
        this.elevatorIOSimulation = elevatorIOSimulation;
    }

    @Override
    protected void updateVisualization() {
        System.out.println(elevatorIOSimulation.getMotorInputs().getMotorValue().getAsDouble());
        ElevatorVisualizationMechanism.ELEVATOR.setLength(0.3 + elevatorIOSimulation.getMotorInputs().getMotorValue().getAsDouble());
    }

    public class ElevatorVisualizationMechanism {
        private static final double ELEVATOR_X_POSITION = 1;
        private static final double ELEVATOR_Y_POSITION = 1;
        public static final LoggedMechanismRoot2d elevatorRoot = ROBOT_MECHANISM.getRoot(ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_X_POSITION, ELEVATOR_Y_POSITION);

        private static final double ELEVATOR_LIGAMENT_LENGTH = 0.3;
        private static final double ELEVATOR_LIGAMENT_ANGLE = 0;
        private static final double ELEVATOR_LIGAMENT_LINE_WIDTH = 5;
        public static final Color8Bit ELEVATOR_LIGAMENT_COLOR = new Color8Bit(Color.kRed);
        public static final LoggedMechanismLigament2d ELEVATOR = elevatorRoot.append(
            new LoggedMechanismLigament2d(
                "ELEVATOR",
                ELEVATOR_LIGAMENT_LENGTH, ELEVATOR_LIGAMENT_ANGLE,
                ELEVATOR_LIGAMENT_LINE_WIDTH, ELEVATOR_LIGAMENT_COLOR
            )
        );
    }
}