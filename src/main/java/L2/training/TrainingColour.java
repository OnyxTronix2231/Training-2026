package L2.training;

public class TrainingColour {
   private int red;
  private int green;
   private int blue;
private TrainingColour(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
}
        public static void main(String[] args) {
            TrainingColour blueColour = new TrainingColour(0,0,255);
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
