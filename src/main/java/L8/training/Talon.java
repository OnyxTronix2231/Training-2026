package L8.training;

public class Talon implements Motor {
    @Override
    public int setSpeed(double percantage) {
        return (int) percantage * 4000;
    }

    @Override
    public String setTarget(int angle) {
        return String.valueOf(angle / 360);
    }
}
