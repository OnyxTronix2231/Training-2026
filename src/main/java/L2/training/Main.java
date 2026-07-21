package L2.training;

public class Main {
    public static void main(String[] args) {
        Color1 blueColor = new Color1(0, 0, 100);
        Color1 greenColor = new Color1(0, 100, 0);

        Color1 facColor = Color1.getFactoredColor(greenColor, 10);
        System.out.println(facColor.getGreen());

        Color1 newColor = Color1.addByFactor(blueColor, greenColor, 25);
        System.out.println(newColor.getGreen());

        greenColor.addToColor(25, 0, 25);

        System.out.println(greenColor.getRed());
        System.out.println(greenColor.checkIfSmaller(50));

        Clock clock = new Clock(30, 40, 16);
        clock.sayTime();

        Point point = new Point(2, 4);
        point.moveDot(2, 2);
        System.out.println(point.disFromMid());
    }
}
