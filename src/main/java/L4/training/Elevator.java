package L4.training;

public class Elevator {
    public enum Company {
        Figma,
        Tableu,
        Solidworks,
        WPI,
        Monday,
        SpikeEssential,
        OnyxScout
    }
    public static int ElevatorTo(Company company) {
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
}
