package L7.training;

import L7.cloth;

import java.awt.*;

public class jeans extends cloth {
    public jeans(Color color, int size, boolean isClean) {
        super(color, size, isClean);
    }

    @Override
    public void wash() {
        super.wash();
        this.Size=this.Size/2;


    }
}
