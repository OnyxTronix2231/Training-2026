package L2.training;

public class Color1 {
    int red;
    int green;
    int blue;

    void newColor1(int blueColor,int redColor,int greenColor){
        green += greenColor;
        red += redColor;
        blue += blueColor;
    }

    boolean checkColor(int value) {
        boolean greenResult = green > value && red>value && blue > value;
        return greenResult;
    }

    public static void main(String[] args) {

        Color1 greenColor = new Color1 ();
        greenColor.red = 0;
        greenColor.green = 255;
        greenColor.blue = 0;
        Color1 blueColor = new Color1 ();
        blueColor.red = 0;
        blueColor.green = 0;
        blueColor.blue = 255;
        Color1 redColor = new Color1 ();

        redColor.red = 255;
        redColor.green = 0;
        redColor.blue = 0;
        System.out.println(redColor.checkColor(100));

    }
}
