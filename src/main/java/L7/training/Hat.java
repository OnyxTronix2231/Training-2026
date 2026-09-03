package L7.training;

public class Hat extends Clothes {
    private String brand;
    public Hat(String Brand, int size, boolean isClean) {
        super(size,isClean);
        brand = Brand;
    }
    @Override
    public void Wash(){
        isClean=true;
        for (int i = 0; i < 6; i++) {
            System.out.println(brand);
        }
    }
}
