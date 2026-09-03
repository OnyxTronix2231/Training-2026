package L7;

import L7.training.Clothes;

public class Jeans  extends Clothes {
    public Jeans(int size, boolean isClean) {
        super(size, isClean);
    }
    @Override
    public void Wash() {
        isClean=true;
        size/=2;

    }
}
