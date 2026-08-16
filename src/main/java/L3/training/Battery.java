package L3.training;

import edu.wpi.first.math.MathUtil;

public class Battery {
    private int volt;

    public Battery(int voltage){
        this.volt = MathUtil.clamp(volt, 0, 15);
    }
    public int getVolt() {return volt;}
    public void setVolt(int newVolt) {volt = MathUtil.clamp(newVolt,0,15);}

    public String toString() {
        return "Battery: "+
                "\n\tVoltage: "+volt;
    }

    public void sayState() {
        if (volt>12)
            System.out.println("Good");
        if (volt<12&&volt>10)
            System.out.println("Not so Good");
        if (volt<10&&volt>6)
            System.out.println("Bad");
        if (volt<6)
            System.out.println("Really Bad");

    }
    public void charge(int hours) {volt = MathUtil.clamp(hours*3+volt,0,15);}}
