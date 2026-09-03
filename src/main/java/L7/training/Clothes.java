package L7.training;

import L7.Shirt;

import java.awt.*;

public abstract class Clothes {
    protected int size;
    protected boolean isClean;

    public Clothes(int Size, boolean IsClean) {
        size = Size;
        isClean = IsClean;

    }

    public void Wash() {
        isClean=true;
    }
    public void Description(Clothes clothes){
        System.out.println(clothes.size);
        System.out.println(clothes.isClean);
        System.out.println(clothes.size);
    }

    public static void main(String[] args) {
        Clothes nigga = new Shirt(20,true,Color.GREEN);
        nigga.Description(nigga);
    }
}
