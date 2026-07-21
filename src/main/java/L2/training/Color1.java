package L2.training;

public class Color1 {
    private int red;
    private int green;
    private int blue;

    public Color1(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    void newColor(int a, int b, int c) {
        red += a;
        green += b;
        blue += c;
    }

    public void setRed(int newRed) {
        red = newRed;
    }
    public void setGreen(int newGreen) {
         green = newGreen;
    }
    public void setBlue(int newBlue) {
         blue = newBlue;
    }
    public int getRed() {
        return red;
    }
    public int getGreen() {
        return green;
    }
    public int gerBlue() {
        return blue;
    }

    boolean bigger(int d) {
        boolean check = red > d && green > d && blue > d;
        return check;
    }

    public void reset() {
        red = 0;
        green = 0;
        blue = 0;
    }

    public static Color1 getFactoredColor(Color1 c, int percentage) {
        Color1 c2 = new Color1(c.red * dfghjkl, c.green * fhfh, c.blue * ggt)
        return c2;
    }

        public static void main(String[] args) {
        Color1 grenncolor = new Color1(0, 255, 0);
        Color1 blueColor = new Color1(0,0,200);
        grenncolor.newColor(255,-100,0);
        System.out.println(grenncolor.green);
        boolean c = grenncolor.bigger(80);
        System.out.println(c);
        grenncolor.reset();
        System.out.println(grenncolor.red);

    }
}
