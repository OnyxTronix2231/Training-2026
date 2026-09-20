package frc.robot.lib;

import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

public class VisualizationLib {
    public static void generateVisualizedWheel(LoggedMechanismLigament2d[] WHEEL, int partCount, LoggedMechanismRoot2d wheelRoot, String prefix, double ligamentLength, double lineWidth, Color8Bit color) {
        for (int i = 0; i < partCount; i++) {
            WHEEL[i] = wheelRoot.append(new LoggedMechanismLigament2d(
                prefix + i,
                ligamentLength,
                ((360.0/partCount) * i),
                lineWidth,
                color
            ));
        }
    }

    public static void generateVisualizedWheel(LoggedMechanismLigament2d[] WHEEL, int partCount, LoggedMechanismRoot2d wheelRoot, String prefix, Color8Bit color) {
        for (int i = 0; i < partCount; i++) {
            WHEEL[i] = wheelRoot.append(new LoggedMechanismLigament2d(
                prefix + i,
                0.1,
                ((360.0/partCount) * i),
                5,
                color
            ));
        }
    }

    public static void updateVisualizedWheel(LoggedMechanismLigament2d[] WHEEL, double RPS, double visualizedMultiplier) {
        for (LoggedMechanismLigament2d wheelPart : WHEEL) {
            wheelPart.setAngle(wheelPart.getAngle() + (RPS * visualizedMultiplier));
        }
    }
}
