package L7.training;

import java.awt.*;

public class Jeans extends Clothes {

    public Jeans(int size, boolean isClean) {
        super(size, isClean);
    }


    @Override
    public void wash() {
        size /= 2;
        this.isClean = true;
    }
}
