package L8.training;

import java.util.function.DoubleSupplier;

public class RealRobot {
    private Wheel leftWheel;
    private Wheel rightWheel;
    private DoubleSupplier joyStick;

    public RealRobot(){
        robotInit();
    }

    public void robotInit(){
        this.joyStick = ()-> Math.random();
        this.leftWheel = new Wheel(new NEO(), joyStick);
        this.rightWheel = new Wheel(new Talon(),joyStick);
    }

    public void periodic(){
        leftWheel.moveMotor();
        rightWheel.moveMotor();
    }

}
