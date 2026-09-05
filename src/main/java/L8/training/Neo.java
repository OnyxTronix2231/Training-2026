package L8.training;

public class Neo implements  motor {
    @Override
    public double setSpeed(double per) {
        return per*10000;
    }

    @Override
    public String setTarget(int angle) {
        int encorder=0;
        encorder=angle/360*2048;
        String s=""+encorder;
        return s;
    }
}
