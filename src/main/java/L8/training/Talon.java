package L8.training;

public class Talon implements motor {
    @Override
    public String setTarget(int angle) {
        int RPM=0;
        RPM=angle/360*4000;
        String s=""+RPM;

        return "";
    }

    @Override
    public double setSpeed(double per) {
        return per*4000;
    }
}
