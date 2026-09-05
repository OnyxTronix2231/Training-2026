package L8.training;

import java.util.function.DoubleSupplier;

public class Roobot {
    private Wheel leftWheel;
    private Wheel rightWheel;
    private DoubleSupplier controler;

    public Roobot() {
        robotInit();
    }

    public void robotInit() {
        Motor rightMotor = new NEO();
        Motor leftMotor = new Talon();
        controler = ()-> Math.random();
        leftWheel = new Wheel(leftMotor, controler);
        rightWheel = new Wheel(rightMotor, controler);
    }

    public void periodic() {
        leftWheel.moveMotor();
        rightWheel.moveMotor();
    }
}
