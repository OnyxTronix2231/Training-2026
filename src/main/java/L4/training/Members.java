package L4.training;

public class Members {
    private static int memberCounter = 0;
    private static int shirtCount = 0;
    private shirtSize size;

    public Members(shirtSize size) {
        this.memberCounter++;
        this.size = size;
    }

    public enum shirtSize {
        s,
        m,
        l,
        xl
    }

    public void buyShirt() {
        this.shirtCount++;
    }

    public static boolean canOrder() {
        return shirtCount > 5;
    }

    public static void main(String[] args) {
        Members a = new Members(shirtSize.m);
        Members b = new Members(shirtSize.s);
        System.out.println(Members.memberCounter);
        a.buyShirt();
        b.buyShirt();
        System.out.println(canOrder());
        b.buyShirt();
        b.buyShirt();
        b.buyShirt();
        b.buyShirt();
        System.out.println(canOrder());
        System.out.println(Members.shirtCount);
        System.out.println(b.size);
    }
}
