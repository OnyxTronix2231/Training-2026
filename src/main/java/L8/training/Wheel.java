package L8.training;

import java.util.function.DoubleSupplier;

public class Wheel {
    private Motor motor;
    private DoubleSupplier speed;

    public Wheel(Motor motor, DoubleSupplier speed) {
        this.motor = motor;
        this.speed = speed;
    }

    public void moveMotor(){
        motor.setSpeed(speed.getAsDouble());
    }

    public void stopRobot(){
        motor.setTarget(0);
    }
}
