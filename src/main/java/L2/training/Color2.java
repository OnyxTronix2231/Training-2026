package L2.training;

public class Color2 {

    private int red;
    private int green;
    private int blue;

    public Color2(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    void clear(Color2 c) {
        c.red = 0;
        c.green = 0;
        c.blue = 0;
    }
    public static Color2 GetFactoredColor(Color2 c, int percentage) {
        double mult = 100/percentage;
        int redValue = (int)mult*c.red;
        int greenValue = (int)mult*c.green;
        int blueValue = (int)mult*c.blue;
        Color2 color = new Color2(redValue, greenValue, blueValue);
        return color;
    }
    public static void addByFactor(Color2 c1, Color2 c2, int percentage) {
        double mult = 100/percentage;
        double c2Mult = 1-mult;
        int redValue = (int)mult*c1.red+(int)c2Mult*c2.red;
        int greenValue = (int)mult*c1.green+(int)c2Mult*c2.green;
        int blueValue = (int)mult*c1.blue+(int)c2Mult*c2.blue;

    }
}
