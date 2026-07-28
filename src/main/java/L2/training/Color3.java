package L2.training;

import edu.wpi.first.math.MathUtil;

public class Color3 {
    private int red;
    private int blue;
    private int green;
    public Color3(int newred2,int newblue2,int newgreen2){
        red= MathUtil.clamp(newred2,0,255);
        blue=MathUtil.clamp(newblue2,0,255);
        green=MathUtil.clamp(newgreen2,0,255);
    }
    public static Color3 getFactoredColor(Color3 color, int percentage) {
        return new Color3(
                color.red * percentage / 100,
                color.blue * percentage / 100,
                color.green * percentage / 100
        );



    }

   // public static void main(String[] args) {
       // Color3 color3 = new Color3(200, 200, 200);
      //  System.out.println(Color3.getFactoredColor(200, 25));
    }
