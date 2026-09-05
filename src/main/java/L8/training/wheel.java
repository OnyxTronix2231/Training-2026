package L8.training;

import java.util.function.DoubleSupplier;

public class wheel {
    private motor motor;
    private DoubleSupplier SpeedSupplier;

    public wheel(motor motor, DoubleSupplier speedSupplier) {
        this.motor = motor;
        SpeedSupplier = speedSupplier;
    }
    public void move(){
        motor.setSpeed(1);
        motor.setTarget(0);

    }
}
