package L2.training;

import edu.wpi.first.math.MathUtil;

public class TrainingColour {
   private int red;
  private int green;
   private int blue;
private TrainingColour(int red, int green, int blue) {
    this.red = MathUtil.clamp(red, 0, 255);
    this.green = MathUtil.clamp(green, 0, 255);
    this.blue = MathUtil.clamp(blue, 0, 255);
}
        public static void main(String[] args) {
            TrainingColour blueColour = new  TrainingColour(0,0,255);
            TrainingColour greenColour = new TrainingColour(0,255,0);
            greenColour.addColour(25,-30,25);
            System.out.println(greenColour.red);

        }
    public void addColour(int redNew, int greenNew, int blueNew){
        red += redNew;
        green += greenNew;
        blue += blueNew;
    }
   public boolean isSmall(int num){
        boolean result = num< red, green, blue;
        return result;
    }
        public int getRed(){
    return red;
   }
   public int setRed(int redNew){
    red = redNew;
    return red;
   }
}
