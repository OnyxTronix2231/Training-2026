package L7.training;

import java.awt.*;

public class Shirt extends Clothes {
    private Color color;

    public Shirt(int size, boolean isClean, Color color) {
        super(size, isClean);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static void main(String[] args) {
        Clothes myShirt = new Shirt(5, false, Color.BLUE);
        someThing(myShirt);
    }
}