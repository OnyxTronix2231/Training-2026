package L7;

import L7.lecture.Closet.Clothing;
import L7.training.Shirt;

import java.awt.*;

public class cloth {

    protected int Size;
    protected boolean isClean;

    public cloth(Color color, int size, boolean isClean) {
        Size = size;
        this.isClean = isClean;
    }
    public void wash(){
        isClean=true;
    }

    public int getSize() {
        return Size;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setSize(int size) {
        Size = size;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public static void give(cloth clothing) {
        System.out.println(clothing.Size);
        clothing.wash();
        System.out.println(clothing.getSize());
    }

    public static void main(String[] args) {
        cloth shirt = new Shirt(Color.black,40,false);
        give(shirt);
    }
}
