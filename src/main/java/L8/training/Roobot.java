package L8.training;

import java.util.function.DoubleSupplier;

public class Roobot {
    private Wheel WheelLeft;
    private Wheel WheelRight;
    private DoubleSupplier Joystick;
    public Roobot() {
        RoobotInit(WheelLeft, WheelRight, Joystick);
    }
    public void RoobotInit(Wheel WheelLeft, Wheel WheelRight, DoubleSupplier Joystick) {
        this.Joystick =()->Math.random();
        this.WheelLeft = new Wheel(new Neo(),Joystick);
        this.WheelRight = new Wheel(new Neo(),Joystick);
    }
    public void Periodic(){
        WheelLeft.moveMotor();
        WheelRight.moveMotor();
    }



}
