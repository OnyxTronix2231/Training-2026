package L3.training;

public class Utils {
    public static double max(double max, double num){
        if (max < num){
            return max;
        } else {
            return num;
        }
    }

    public static double min(double min, double num){
        if (min > num){
            return min;
        } else {
            return num;
        }
    }

    public static double clamp(double num, double max, double min){
        return max(min(max, num), min);
    }
}