package L8.training;

public class NEO implements Motor{
    private static final int maxSpeed = 1000;
    private static final int fullCircle = 360;
    private static final int encoder = 2048;

    @Override
    public double setSpeed(double speed){
        return speed * maxSpeed;
    }

    @Override
    public String setTarget(double angle){
        return String.valueOf(angle * encoder / fullCircle);
    }
}
