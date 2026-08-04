package L4.training;

import static L4.training.Elevetor.Floors.Figma;
import static L4.training.Elevetor.Floors.Tableu;

public class Elevetor {
    public static int getfloor(Floors floor){
        switch (floor) {
            case Figma:
                return 10;

            case Tableu:
                return 17;


            case SolidWorks:
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
                return 67;
        }
    }
    public enum Floors{
        Figma,
        Tableu,
        SolidWorks,
        WPI,
        Monday,
        SpikeEssential,
        OnyxScout,

    }

    public static void main(String[] args) {

        System.out.println(getfloor(Figma));
    }
}
