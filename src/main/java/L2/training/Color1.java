package L2.training;

public class Color1 {
    private int red;
    private int green;
    private int blue;

    public Color1(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed(){
        return red;
    }

    public int getGreen(){
        return green;
    }

    public int getBlue(){
        return blue;
    }

    public void setRed(int newRed){
        red = newRed;
    }

    public void setGreen(int newGreen){
        green = newGreen;
    }

    public void setBlue(int newBlue){
        blue = newBlue;
    }

    public void lowerLight() {
        red /= 2;
        green /= 2;
        blue /= 2;
    }

    public void addToColor(int r, int g, int b) {
        red += r;
        green += g;
        blue += b;
    }

    public boolean checkIfSmaller(int num) {
        boolean check = num < red && num < green && num < blue;
        return check;
    }

    private void clear(){
        red = 0;
        green = 0;
        blue = 0;
    }

    public static Color1 getFactoredColor(Color1 color, int num){
        color.setRed(color.getRed() * num/100);
        color.setGreen(color.getGreen() * num / 100);
        color.setBlue(color.getBlue() * num/100);

        return color;
    }

    public static Color1 addByFactor(Color1 color1, Color1 color2, int num){
        Color1 firstColor = Color1.getFactoredColor(color1, num);
        Color1 secondColor = Color1.getFactoredColor(color2,100 - num);

        int newRed = firstColor.getRed() + secondColor.getRed();
        int newGreen = firstColor.getGreen() + secondColor.getGreen();
        int newBlue = firstColor.getBlue() + secondColor.getBlue();
        Color1 newColor = new Color1(newRed, newGreen, newBlue);

        return newColor;
    }
}
