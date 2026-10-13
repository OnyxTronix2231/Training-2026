package frc.robot.lib.VectorMath;

import edu.wpi.first.math.geometry.Translation2d;
import org.littletonrobotics.junction.Logger;

public class LineFunction {

    public Translation2d TCoefficient;
    public Translation2d vectorCoefficient;

    public double xCoefficient;
    public double oneCoefficient;

    public LineFunction(Translation2d TCoefficient, Translation2d vectorCoefficient) {
        this.TCoefficient = TCoefficient;
        this.vectorCoefficient = vectorCoefficient;
    }

    public Translation2d getTCoefficient() {
        return TCoefficient;
    }

    public double getXCoefficient() {
        return xCoefficient;
    }

    public double getOneCoefficient() {
        return oneCoefficient;
    }

    public LineFunction(double xCoefficient, double oneCoefficient) {
        this.xCoefficient = xCoefficient;
        this.oneCoefficient = oneCoefficient;
    }

    public Translation2d getNumCoefficient() {
        return vectorCoefficient;
    }
}