package L4.training;

public class Member {
    private String size;
    public static int member = 0;
    public static int shirtCount = 0;

    public Member(String size) {
        this.size = size;
        member++;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String newSize) {
        size = newSize;
    }

    public enum size {
        XSmall,
        Small,
        Medium,
        Large,
        XLarge
    }

    public boolean canOrder(int count) {
        return count > 5;
    }

    public void orderShirt(int count) {
        shirtCount += count;
    }

}
