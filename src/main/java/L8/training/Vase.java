package L8.training;

import java.awt.*;

public class Vase implements Breakable {
    private boolean isBroken;
    private Color color;

    @Override
    public int breakIt(double speed) {
        return 10;
    }

    @Override
    public boolean fix() {
        return false;
    }
}

