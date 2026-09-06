package L8.training.UnizRobot;

public class NEO implements Motor{
    @Override
    public double setSpeed(double presnt) {
        return 10000/presnt;
    }

    @Override
    public String setTarget(int place) {
        return "" + place/360*2048;
    }

}