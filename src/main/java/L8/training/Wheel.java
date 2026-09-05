package L8.training;

import java.util.function.DoubleSupplier;

public class Wheel {
    private Motor motor;
    private DoubleSupplier speedSupplier;

    public Wheel(Motor motor, DoubleSupplier speedSupplier) {
        this.motor = motor;
        this.speedSupplier = speedSupplier;
    }
    public void moveMotor() {
        motor.setSpeed(speedSupplier.getAsDouble());
    }
    public void stopMotor() {
        motor.setSpeed(0);
    }

}
