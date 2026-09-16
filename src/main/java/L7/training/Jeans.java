package L7.training;

public class Jeans extends Clothes{

    public Jeans(boolean isClean, int size) {
        super(isClean, size);
    }

    @Override
    public void wash(){
        isClean = true;
        size /= 2;
    }
}
