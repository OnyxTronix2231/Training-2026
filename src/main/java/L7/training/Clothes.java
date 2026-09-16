package L7.training;

public class Clothes {
    protected int size;
    protected boolean isClean;

    public Clothes(boolean isClean, int size) {
        this.isClean = isClean;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public void wash(){
        isClean = true;
    }

    public static void beforeAndAfterWash(Clothes clothe){
        System.out.println(clothe.size);
        clothe.wash();
        System.out.println(clothe.size);
    }
}
