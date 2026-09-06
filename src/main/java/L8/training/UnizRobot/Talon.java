package L8.training.UnizRobot;

public class Talon implements Motor {
    @Override
    public String setTarget(int place) {
        return "" + place/360;

    }

    @Override
    public double setSpeed(double presnt) {
        return 4000/presnt;
    }

}
