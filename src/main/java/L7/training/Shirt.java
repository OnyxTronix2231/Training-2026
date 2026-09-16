package L7.training;

import java.awt.*;

public class Shirt extends Clothes{
    private Color color;

    public Shirt(boolean isClean, int size, Color color) {
        super(isClean, size);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
