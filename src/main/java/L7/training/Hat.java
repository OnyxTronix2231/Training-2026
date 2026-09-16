package L7.training;

public class Hat extends Clothes{
    private Shirt brand;

    public Hat(boolean isClean, int size, Shirt brand) {
        super(isClean, size);
        this.brand = brand;
    }

    public Shirt getBrand() {
        return brand;
    }

    public void setBrand(Shirt brand) {
        this.brand = brand;
    }

    public void showoff(){
        for (int i = 0; i < 5; i++) {
            System.out.println(brand);
        }
    }
}
