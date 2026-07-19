package L2.training;

public class color1 {
    int red;
    int blue;
    int green;
    void color(int new_red, int new_blue, int new_green) {
        red=new_red+red;
        blue=new_blue+blue;
        green=new_green+green;
    }
    boolean issmall(int num){
        boolean result =num<(red+green+blue);
        return result;

        }



    public static void main(String[] args) {
        color1 greencolor = new color1();
        greencolor.red=0;
        greencolor.green=255;
        greencolor.blue=0;
        greencolor.color(50,50,50);
        boolean c = greencolor.issmall(255);
        color1 blue = new color1();
        blue.red=0;
        blue.green=0;
        blue.blue=255;
        System.out.println(c);
    }
}
