package L2.training;

import java.awt.*;
import edu.wpi.first.math.MathUtil;

public class Color1 {

    private int red;
    private int green;
    private int blue;

    public Color1(int red, int green, int blue) {
        this.red = MathUtil.clamp(red, 0, 255);
        this.green = MathUtil.clamp(green, 0, 255);
        this.blue = MathUtil.clamp(blue, 0, 255);
    }

    public int getRed() {
        return red;
    }
    public int getGreen() {
        return green;
    }
    public int getBlue() {
        return blue;
    }

    public void setRed(int newRed) {
        red = MathUtil.clamp(newRed, 0, 255);
    }
    public void setGreen(int newGreen) {
        green = MathUtil.clamp(newGreen, 0, 255);
    }
    public void setBlue(int newBlue) {
        blue = MathUtil.clamp(newBlue, 0, 255);
    }

    public String toString() {
        String redMsg = "Red: "+red;
        String greenMsg = "Green: "+green;
        String blueMsg = "Blue: "+blue;
        return "Color:" +
                "\n\t"+redMsg+
                "\n\t"+greenMsg+
                "\n\t"+blueMsg;
    }

    void LowerLight() {
        red/=2;
        green/=2;
        blue/=2;
    }
    int getLightAverage() {
        int avg = (red + green + blue) / 3;
        return avg;
    }
    boolean isHighGreen(int newGreen) {
        boolean result = newGreen < green;
        return result;
    }
    void addColor(int newRed, int newBlue, int newGreen) {
        red += MathUtil.clamp(newRed, 0, 255);
        green += MathUtil.clamp(newGreen, 0, 255);
        blue += MathUtil.clamp(newBlue, 0, 255);
    }
    boolean isSmaller(int val) {
        boolean smaller = val < red && val < green && val <blue;
        return smaller;
    }
}