package L8.training;

import java.awt.*;
import java.util.function.DoubleSupplier;

public class robot  {
    private wheel wheelR;
    private wheel wheelL;
    private DoubleSupplier controller;

    public robot() {
        robotinit();
    }
    public void robotinit(){

        this.controller= ()-> Math.random();
        this.wheelR=new wheel(new Neo(),controller);
        this.wheelL=new wheel(new Talon(),controller);

    }
    public void piriodic(){
        wheelR.move();
        wheelL.move();


    }
}