package L4.training;

public class TeamMembers {
    ShirtSizes ShirtSize;
    public TeamMembers(ShirtSizes shirtSize) {
        NumTeamMembers++;
        this.ShirtSize = shirtSize;
    }
    public static void Buyshirt(){
        NumShirtsNeeded++;
    }


    private static int NumTeamMembers= 0;
    private static int NumShirtsNeeded=0;

    public static void main(String[] args) {
        TeamMembers Daniel = new TeamMembers(ShirtSizes.Medium);
        Daniel.Buyshirt();
        Daniel.Buyshirt();
        System.out.println(NumTeamMembers);
        System.out.println(NumShirtsNeeded);

    }


}
