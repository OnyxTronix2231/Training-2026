package L4.training;

public class First {
    public enum teamName {
        Orbit,
        OnyxTronix,
        BumbleBee,
        GrennBlitz,
        Demacia
    }

    public static int teamNumber(teamName name) {
        switch (name) {
            case Orbit:
                System.out.println("blue");
                return 1690;
            case OnyxTronix:
                System.out.println("red");
                return 2231;
            case BumbleBee:
                System.out.println("yellow");
                return 3339;
            case GrennBlitz:
                System.out.println("green");
                return 4590;
            case Demacia:
                System.out.println("purple");
                return 5635;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        teamName team = teamName.OnyxTronix;
        int number = teamNumber(team);
        System.out.println(number);
    }
}
