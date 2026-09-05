package L8.training;

public class Talon implements Motor {
    public static final int maxSpeed = 4000;
    @Override
    public int setSpeed(double percentage) {
        return (int)(percentage*4000);
    }
    @Override
    public String setTarget(int angle) {
        double place = 1;
        place = angle/100;
        return ""+place;
    }
}
