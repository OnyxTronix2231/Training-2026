package L7.training;

import L7.cloth;

import java.awt.*;

public class hat extends cloth {
    private String brand;

    public hat(Color color, int size, boolean isClean, String brand) {
        super(color, size, isClean);
        this.brand = brand;
    }

    @Override
    public void wash() {
        super.wash();
    }
    public void showoff(){
        for (int i = 0; i < 5; i++) {
            System.out.println(brand);

        }
    }
}
