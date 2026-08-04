package L4.training;

public class Enum {
    public enum Companies {
        Figma,
        Tableu,
        Solidworks,
        WPI,
        Monday,
        SpikeEssential,
        OnyxScout
    }

    public static int elevator(Companies company) {
        switch (company) {
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
        Companies worker = Companies.Monday;
        int floor = elevator(worker);
        System.out.println(floor);
    }
}