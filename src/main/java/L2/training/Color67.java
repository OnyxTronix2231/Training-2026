package L2.training;

public class Color67 {
    private int red;
    private int blue;
    private int green;
    public Color67(int red, int blue, int green) {
        this.red=red;
        this.blue=blue;
        this.green=green;

    }
    public String toString(){
        String redmessage ="red:"+red;
        String bluemessage="blue:"+blue;
        String greenmessage="green;"+green;
        return redmessage + "," +greenmessage + "," + bluemessage;
    }

    public static void main(String[] args) {
        Color67 color67=new Color67(255,0,0);
        System.out.println(color67);
    }
}


