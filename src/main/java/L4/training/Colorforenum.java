package L4.training;

public class Colorforenum {
    public enum groups {
        Orbit,
        OnyxTronix,
        BumbleB,
        GreenBlitz,
        Demaica,
    }
    public static int first (groups hi){
        switch (hi){
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
            case Demaica:
                return 5635;
            System.out.println("purple");
            default:
                return 0;
        }


    }
}
