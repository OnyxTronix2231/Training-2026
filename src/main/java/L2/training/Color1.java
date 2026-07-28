package L2.training;
import edu.wpi.first.math.MathUtil;
public class Color1 {
   private int red;
    private int green;
    private int blue;

    public Color1(int red,int green,int blue){
        this.red = MathUtil.clamp(red,0,255);
        this.green = MathUtil.clamp(green,0,255);
        this.blue = MathUtil.clamp(blue,0,255);
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

    public static void main(String[] args) {

        Color1 greenColor = new Color1 (0,255,0);
        Color1 blueColor = new Color1 (0,0,255);
        Color1 redColor = new Color1 (255,0,0);
        System.out.println(redColor.checkColor(100));

    }
}
