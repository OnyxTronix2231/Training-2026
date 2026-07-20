package L2.training;

public class Color1 {
    int red;
    int green;
    int blue;

    void lowerLight() {
        red /= 2;
        green /= 2;
        blue /= 2;
    }

    void addToColor(int r, int g, int b) {
        red += r;
        green += g;
        blue += b;
    }

    boolean checkIfSmaller(int num) {
        boolean check = num < red && num < green && num < blue;
        return check;
    }
    public static void main(String[] args) {
        Color1 blueColor = new Color1();
        blueColor.red = 0;
        blueColor.green = 0;
        blueColor.blue = 255;

        Color1 greenColor = new Color1();
        greenColor.red = 0;
        greenColor.green = 255;
        greenColor.blue = 0;

        greenColor.addToColor(25, -30, 25);
        System.out.println(greenColor.red);

        System.out.println(greenColor.checkIfSmaller(50));
    }
}
