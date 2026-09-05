package L8.training;

public class Neo implements Motor {
    public static final int maxSpeedNeo = 10000;
    @Override
    public int  setSpeed(double percentage) {
        double speed=1;
        speed = percentage*10000;
        return (int)speed;
    }
    @Override
    public String setTarget(int angle){
        double position = 1;
        position = (angle/360)*2048;
        return ""+position;
    }
}
