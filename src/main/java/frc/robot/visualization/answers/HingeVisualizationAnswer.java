package frc.robot.visualization.answers;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.answers.L4.Hinge.HingeIOSimulation;
import frc.robot.visualization.VisualizedSubsystem;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.subsystems.answers.L4.Hinge.HingeConstants.HINGE_SUBSYSTEM_NAME;
import static frc.robot.subsystems.answers.L4.Hinge.HingeConstants.HingeSimulationConstants.HINGE;

public class HingeVisualizationAnswer extends VisualizedSubsystem {
    HingeIOSimulation hingeIOSimulation;

    public HingeVisualizationAnswer(HingeIOSimulation hingeIOSimulation) {
        this.hingeIOSimulation = hingeIOSimulation;
    }

    @Override
    protected void updateVisualization() {
        HINGE.setAngle(hingeIOSimulation.getMotorOutputs().getMotorValue().getAsDouble());
    }
}
