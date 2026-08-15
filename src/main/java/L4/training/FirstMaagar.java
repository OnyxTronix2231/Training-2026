package L4.training;

public class FirstMaagar {
    public enum Teams{
        Orbit,
        OnyxTronix,
        BumbleB,
        GreenBlitz,
        Demacia
    }

    public static int getTeamNUmber(Teams team){
        switch (team){
            case Orbit:
                System.out.println("blue");
                return 1690;
            case OnyxTronix:
                System.out.println("red");
                return 2231;
            case BumbleB:
                System.out.println("yellow");
                return 3339;
            case GreenBlitz:
                System.out.println("green");
                return 4590;
            case Demacia:
                System.out.println("purple");
                return 5636;
        }
        return 0;
    }
}
