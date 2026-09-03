package L7.training;

import L7.cloth;

import java.awt.*;

public class Shirt extends cloth {
    protected Color color;

    public Shirt(Color color, int size, boolean isClean, Color color1) {
        super(color, size, isClean);
        this.color = color1;
    }

    @Override
    public void wash() {
        super.wash();
    }
}
