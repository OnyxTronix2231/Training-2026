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

    public String toString() {
    String redPrint = "Red: " + red;
    String greenPrint = "Green: " + green;
    String bluePrint = "Blue: " + blue;
    return "Colour: \n \t" + redPrint+ "\n \t" + greenPrint + "\n \t" + bluePrint ;
    }

    public static void main(String[] args) {
        TrainingColour colour = new TrainingColour(255, 0, 0);
        System.out.println(colour);
    }

}
