package L3.training;

public class Utils {
    public int max(int a, int b) {
        if (a>b)
            return a;
        else return b;
        }
        public  int min(int a, int b) {
        if (a<b)
            return a;
        else return b;
        }
        public static int clamp(int value, int lowerBound, int upperBound) {
        if (value<lowerBound)
            return lowerBound;
        if (value>upperBound){
            return upperBound;}
           else {
               return value;
        }
        }

    public static void main(String[] args) {
        System.out.println(Utils.clamp(6667, 0,255));
    }
    }


