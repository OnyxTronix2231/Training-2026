package L3.training;

import edu.wpi.first.math.MathUtil;

public class Battery {
    private int volt;
    public Battery(int volt) {
        this.volt = MathUtil.clamp(volt, 0,15);
    }
    public void setVolt(int newVolt) {volt = MathUtil.clamp(newVolt,0,15);}
    public int getVolt() {return volt;}

    public void sayState() {
        if (volt > 12) {
            System.out.println("Good");
        } else if (volt >= 10) {
            System.out.println("Not so good");
        } else if (volt >= 6) {
            System.out.println("Bad");
        } else {
            System.out.println("Really bad");
        }
    }

    public void charge(int hours) {
        volt = MathUtil.clamp(volt + hours * 3,0,15);
    }

    @Override
    public String toString() {
        String voltMessage = "The battery has " + volt + "v";
        return voltMessage;
    }

    public static void main(String[] args) {
        Battery myBattery = new Battery(13);
        myBattery.sayState();
        System.out.println(myBattery.toString());
        myBattery.charge(5);
        System.out.println(myBattery.toString());

    }
}
