package L4.training;

public class FriendlyElevator {
    public static int Floor(Companies companies) {
        switch (companies) {
            case Figma:
                return 10;
            case Tableu:
                return 17;
            case Solidworks:
                return 23;
            case WPI:
                return 30;
            case Monday:
                return 32;
            case SpikeEssential:
                return 38;
            case OnyxScout:
                return 41;
            default:
                return 0;


        }
    }

    public static void main(String[] args) {
        System.out.println(Floor(Companies.Figma));
    }
}
