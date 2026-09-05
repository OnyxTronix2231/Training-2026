package L8.training;

public class NEO implements Motor {
    @Override
    public int setSpeed(double percantage) {
        return (int) percantage * maxIncoderPerSecond;
    }

    @Override
    public String setTarget(int angle) {
        return String.valueOf(angle / 360 * incodersPerRound);
    }
}
