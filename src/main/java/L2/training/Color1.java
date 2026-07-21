package L2.training;

public class Color1 {
   private int red;
    private int green;
    private int blue;

    public Color1(int red,int green,int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }


    public void newColor1(int blueColor,int redColor,int greenColor){
        this.green += greenColor;
        this.red += redColor;
        this.blue += blueColor;
    }

   public boolean checkColor(int value) {
        boolean greenResult = green > value && red>value && blue > value;
        return greenResult;
    }

    public void setRed(int newred){
        red = newred;

    }
    public int getRed(){
        return red;
    }
    public void setgreen(int newgreen){
        green = newgreen;

    }
    public int getgreen(){
        return green;
    }
    public void setBlue(int newblue){
        green = newblue;

    }
    public int getBlue(){
        return blue;
    }
    public void clear (){
        red = 0;
        green=0;
        blue=0;
    }
    public static Color1 getFactoredColor(Color1 c, int percent){
        int newRed
    }

    public static void main(String[] args) {

        Color1 greenColor = new Color1 (0,255,0);
        Color1 blueColor = new Color1 (0,0,255);
        Color1 redColor = new Color1 (255,0,0);
        System.out.println(redColor.checkColor(100));

    }
}
