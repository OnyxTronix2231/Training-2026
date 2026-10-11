package frc.robot.lib;

import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import java.util.function.UnaryOperator;

public class VisualizationLib {
    public static class Wheel {
        private static final int SPIKE_COUNT = 4;
        private static final double DEFAULT_SPIKE_LENGTH = 0.1;
        private static final double DEFAULT_LINE_WIDTH = 5;

        private final LoggedMechanismLigament2d[] spikes = new LoggedMechanismLigament2d[SPIKE_COUNT];
        private double spikeLength = DEFAULT_SPIKE_LENGTH;
        private double lineWidth = DEFAULT_LINE_WIDTH;
        private Color8Bit color;
        private double angle = 0;

        public Wheel(LoggedMechanismRoot2d root, String name, Color8Bit color) {
            this(root::append, name, color);
        }

        public Wheel(LoggedMechanismLigament2d parent, String name, Color8Bit color) {
            this(parent::append, name, color);
        }

        private Wheel(UnaryOperator<LoggedMechanismLigament2d> append, String name, Color8Bit color) {
            this.color = color;
            for (int i = 0; i < SPIKE_COUNT; i++) {
                spikes[i] = append.apply(new LoggedMechanismLigament2d(name + i, spikeLength, spikeAngle(i), lineWidth, color));
            }
        }

        public void spin(double degrees) {
            setAngle(angle + degrees);
        }

        public int getSpikeCount() {
            return SPIKE_COUNT;
        }

        public double getAngle() {
            return angle;
        }

        public void setAngle(double angle) {
            this.angle = angle;
            for (int i = 0; i < SPIKE_COUNT; i++) {
                spikes[i].setAngle(spikeAngle(i));
            }
        }

        public double getSpikeLength() {
            return spikeLength;
        }

        public void setSpikeLength(double spikeLength) {
            this.spikeLength = spikeLength;
            for (LoggedMechanismLigament2d spike : spikes) {
                spike.setLength(spikeLength);
            }
        }

        public double getLineWidth() {
            return lineWidth;
        }

        public void setLineWidth(double lineWidth) {
            this.lineWidth = lineWidth;
            for (LoggedMechanismLigament2d spike : spikes) {
                spike.setLineWeight(lineWidth);
            }
        }

        public Color8Bit getColor() {
            return color;
        }

        public void setColor(Color8Bit color) {
            this.color = color;
            for (LoggedMechanismLigament2d spike : spikes) {
                spike.setColor(color);
            }
        }

        private double spikeAngle(int index) {
            return angle + (360.0 / SPIKE_COUNT) * index;
        }
    }
}
