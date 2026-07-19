package L2.training;

public class TrainingColour {
    int red;
    int green;
    int blue;

    void addColour(int redNew, int greenNew, int blueNew){
        red += redNew;
        green += greenNew;
        blue += blueNew;
    }
    boolean isSmall(int num){
        boolean result = num< red, green, blue;
        return result;
    }

    public static void main(String[] args) {
        TrainingColour blueColour = new TrainingColour();
        blueColour.red = 0;
        blueColour.green = 0;
        blueColour.blue = 255;
        TrainingColour greenColour = new TrainingColour();
        greenColour.green = 255;
        greenColour.blue = 0;
        greenColour.red = 0;

        greenColour.addColour(25,-30,25);
        System.out.println(greenColour.red);

    }
}
