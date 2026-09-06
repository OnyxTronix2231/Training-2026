package L8.training.UnizRobot;

import java.util.function.DoubleSupplier;

public class Wheel{
    Motor motor;
    DoubleSupplier SpeedSuplier;
    public Wheel(Motor motor, DoubleSupplier SpeedSuplaier) {
        this.motor = motor;
        this.SpeedSuplier = SpeedSuplaier;
    }
    public void moveMotor (){
        motor.setSpeed(SpeedSuplier.getAsDouble());
    }
    public void stopMotor (){
        motor.setSpeed(0);
        motor.setTarget(0);
    }

}
