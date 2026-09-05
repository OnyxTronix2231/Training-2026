package L8.training;

public interface Motor {

    public static final double percentage = 0.5;
    public static final int angle = 90;
    public static final int maxIncoderPerSecond = 10000;
    public static final int incodersPerRound = 2048;
    public static final int maxRPM = 4000;

    int setSpeed(double percantage);
    String setTarget(int angle);
}
