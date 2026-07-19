package L2.training;

public class Color1 {
    int red;
    int green;
    int blue;

    void newColor(int a, int b, int c) {
        red += a;
        green += b;
        blue += c;
    }
    boolean bigger(int d) {
        boolean check = red > d && green > d && blue > d;
        return check;
    }

    public static void main(String[] args) {
        Color1 grenncolor = new Color1();
        grenncolor.red = 0;
        grenncolor.green = 255;
        grenncolor.blue = 0;

        grenncolor.newColor(255,-100,0);
        System.out.println(grenncolor.green);
        boolean c = grenncolor.bigger(80);
        System.out.println(c);
    }
}
