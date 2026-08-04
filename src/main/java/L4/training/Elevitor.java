package L4.training;

public class Elevitor {
    public static void main(String[] args) {
        Floor floor = Floor.Monday;
        floor1 (floor);
    }
    public enum Floor{
        Figma,
        Tableu,
        Solidworks,
        WPI,
        Monday,
        SpikeEssential,
        OnyxScout
    }
    public static int floor1 (Floor floor){
        switch (floor){
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
