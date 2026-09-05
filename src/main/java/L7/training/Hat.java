package L7.training;

import java.awt.*;

public class Hat extends Clothes {
    private String brand;

    public Hat(int size, boolean isClean, String brand) {
        super(size, isClean);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public static void showOff(String brand) {
        for (int i = 0; i < 5; i++) {
            System.out.println(brand);
        }
    }
}
