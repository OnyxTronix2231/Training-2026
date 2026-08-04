package L4.training;

public class FIRST {
    public enum Firstgroups{
        Orbit,
        OnyxTronix,
        Greenblitz,
        Demacia,
    }
    public static int firstdata(Firstgroups group){
        switch (group){
            case Orbit:
                System.out.println("Blue");
                return 1690;
            case OnyxTronix:
                System.out.println("Red");
                return 2231;
            case Greenblitz:
                System.out.println("Green");
                return 4590;
            case Demacia:
                System.out.println("Purple");
                return 5635;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(firstdata(Firstgroups.Demacia));
    }
}
