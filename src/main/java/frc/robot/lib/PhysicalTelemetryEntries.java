package frc.robot.lib;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import java.util.function.DoubleSupplier;

public class PhysicalTelemetryEntries {

    private GenericEntry targetValue;

    public PhysicalTelemetryEntries(String tabName, String prefix, DoubleSupplier positionSupplier, DoubleSupplier velocitySupplier, DoubleSupplier accelerationSupplier, double defaultAngle) {
        ShuffleboardTab tab = Shuffleboard.getTab(tabName);

        tab.addDouble(prefix + " position", positionSupplier);
        tab.addDouble(prefix + " velocity", velocitySupplier);
        tab.addDouble(prefix + " acceleration", accelerationSupplier);

        this.targetValue = tab.add(prefix + " target position", defaultAngle).getEntry();
        tab.addDouble(prefix + " position error", () -> positionSupplier.getAsDouble() - targetValue.getDouble(0));
    }

    public PhysicalTelemetryEntries(String tabName, String prefix, DoubleSupplier velocitySupplier, DoubleSupplier accelerationSupplier, double defaultVelocity) {
        ShuffleboardTab tab = Shuffleboard.getTab(tabName);

        tab.addDouble(prefix + " velocity", velocitySupplier);
        tab.addDouble(prefix + " acceleration", accelerationSupplier);

        this.targetValue = tab.add(prefix + " target velocity", defaultVelocity).getEntry();
        tab.addDouble(prefix + " velocity error", () -> velocitySupplier.getAsDouble() - targetValue.getDouble(0));
    }

    public double getTargetPosition() {
        return targetValue.getDouble(0);
    }

    public double getTargetVelocity() {
        return targetValue.getDouble(0);
    }
}