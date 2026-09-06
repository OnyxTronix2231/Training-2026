package L8.training.UnizRobot;

import java.util.function.DoubleSupplier;

public class UnizRobot {
    private Wheel wheel1;
    private Wheel wheel2;
    private DoubleSupplier controler;

    public UnizRobot() {
        robotInit();
    }
    public void robotInit (){
        controler = ()-> Math.random();
        this.wheel1 = new Wheel(new NEO(), controler);
        this.wheel2 = new Wheel(new Talon(),controler);
    }
    public void periodic(){
        wheel1.moveMotor();
        wheel2.moveMotor();
    }
}
