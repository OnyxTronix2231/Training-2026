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

    public void newColor(int a, int b, int c) {
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

    public boolean bigger(int d) {
        boolean check = red > d && green > d && blue > d;
        return check;
    }

    public void reset() {
        red = 0;
        green = 0;
        blue = 0;
    }

    public static Color1 getFactoredColor(Color1 c, int percentage) {
        return new Color1(c.red * percentage/100, c.green * percentage/100, c.blue * percentage/100);
    }

    public static Color1 addByFactor(Color1 c1, Color1 c2, int percentage) {
        int percentage2 = 100 - percentage;
        Color1 a = getFactoredColor(c1, percentage);
        Color1 b = getFactoredColor(c2,percentage2);
        return new Color1(a.red + b.red, a.green + b.green, a.blue + b.blue);


    }

    @Override
    public String toString() {
        return "Color1{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }

    public static void main(String[] args) {
        Color1 greencolor = new Color1(0, 255, 0);
        Color1 blueColor = new Color1(0,0,200);
        greencolor.newColor(255,-100,0);
        //System.out.println(greencolor.green);
        boolean c = greencolor.bigger(80);
        //System.out.println(c);
        //greencolor.reset();
        //System.out.println(greencolor.red);
        Color1 darkgreen = getFactoredColor(greencolor, 50);
        //System.out.println(darkgreen.green);
        Color1 combined =  addByFactor(darkgreen,greencolor,50);
        System.out.println(darkgreen.toString());
        System.out.println(greencolor.toString());
        System.out.println(combined.toString());

    }
}
