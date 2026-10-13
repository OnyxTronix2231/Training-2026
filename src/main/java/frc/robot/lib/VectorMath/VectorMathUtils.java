package frc.robot.lib.VectorMath;

import edu.wpi.first.math.geometry.Translation2d;
import frc.robot.lib.Point;

public class VectorMathUtils {

    public static LineFunction getFunction(Point pointA, Point pointB) {
        return new LineFunction(new Translation2d(pointB.x() - pointA.x(), pointB.y() - pointA.y()), new Translation2d(pointA.x(), pointA.y()));
    }

    public static Point findPoint(LineFunction lineFunction, double t) {
        return new Point(lineFunction.getNumCoefficient().getX() + lineFunction.getTCoefficient().getX() * t, lineFunction.getNumCoefficient().getY() + lineFunction.getTCoefficient().getY() * t);
    }

    public static Point findIntersectionPoint(LineFunction referenceLineFunction, LineFunction targetLineFunction) {
        double x = targetLineFunction.getNumCoefficient().getX() - referenceLineFunction.getNumCoefficient().getX();
        LineFunction tFunction = new LineFunction(targetLineFunction.getTCoefficient().getX() / referenceLineFunction.getTCoefficient().getX(), x / referenceLineFunction.getTCoefficient().getX());
        double y = targetLineFunction.getNumCoefficient().getY() - referenceLineFunction.getNumCoefficient().getY() - (tFunction.getOneCoefficient() * referenceLineFunction.getTCoefficient().getY());
        double sCoefficient = referenceLineFunction.getTCoefficient().getY() * tFunction.getXCoefficient() - targetLineFunction.TCoefficient.getY();
        double s = y / sCoefficient;
        return findPoint(targetLineFunction, s);
    }
}
