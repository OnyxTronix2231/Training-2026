package L2.training;

public class Color1 {
    private int red;
    private int blue;
    private int green;
    Color1(int red,int green,int blue){
        this.red=red;
        this.green=green;
        this.blue=blue;

    }
    public void setred(int newRed){
        red= newRed;

    }
    public void setBlue(int newBlue){
        blue=newBlue;
    }
    public void setGreen(int newGreen){
        green=newGreen;
    }
    public int getRed(){
        return red;
    }

    public void setRed(int newRed) {
        red = newRed;
    }
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
        Color1 color = new Color1(255,0,0);
        color.color(50,50,50);
        boolean c = color.issmall(255);
        Color1 blue = new Color1(0,0,255);
        System.out.println(c);
    }
}
