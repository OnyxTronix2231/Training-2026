package L8.training;

public class Robot implements Breakable {
    private boolean drivingOrNot;
    private String name;

    public Robot(boolean drivingOrNot, String name) {
        this.drivingOrNot = drivingOrNot;
        this.name = name;
    }

    @Override
    public int breakIt(double speed) {
        return (int) speed / 2;
    }

    @Override
    public boolean fix() {
        return false;
    }
}
