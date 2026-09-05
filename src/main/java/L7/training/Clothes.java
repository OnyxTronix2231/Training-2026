package L7.training;

public class Clothes {
    protected int size;
    protected boolean isClean;

    public Clothes(int size, boolean isClean) {
        this.size = size;
        this.isClean = isClean;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public void wash() {
        this.isClean = true;
    }

    public static void someThing(Clothes beged) {
        System.out.println(beged.size);
        beged.wash();
        System.out.println(beged.size);
    }
}
