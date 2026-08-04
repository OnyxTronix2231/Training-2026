package L4.training;

public class FriendGroup {
    private static int memberCounter = 0;
    private static int shirts = 0;
    private shirtSizes size;
    public FriendGroup(shirtSizes size){
        memberCounter++;
        this.size = size;

    }
    public void buyShirt(){
        shirts++;
    }
    public enum shirtSizes {
        XS,
        S,
        M,
        L,
        XL,
    }
    public static boolean orderShirts() {
        return shirts>=5;
    }
}
