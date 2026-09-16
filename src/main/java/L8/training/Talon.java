package L8.training;

public class Talon implements Motor{
    private static final int maxSpeed = 4000;
    private static final int fullCircle = 360;

    @Override
    public double setSpeed(double speed){
        return speed * maxSpeed;
    }

    @Override
    public String setTarget(double angle){
        return String.valueOf(angle / fullCircle);
    }
}
