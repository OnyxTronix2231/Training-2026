package L8.training.UnizRobot;

import L8.training.Breakable2;

public class Robot implements Breakable2 {
    private String name;
    private boolean move;

    public Robot(String name,boolean move) {
        this.name = name;
        this.move=move;
    }

    @Override
    public int breakit(double speed) {
        return 10;

    }
    @Override
    public boolean fix() {
        return false;
    }
}
