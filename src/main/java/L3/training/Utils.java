package L3.training;

public class Utils {
    public static int bigger(int num1, int num2) {
        if(num1 > num2){
            return num1;
        } else {
            return num2;
        }
    }
    public static int smaller(int num1, int num2) {
        if (num1 > num2) {
            return num2;
        } else {
            return num1;
        }
    }
    public static int clamp(int low, int value, int high){
        if(value > high){
            return high;
        }
        if(value < low){
            return low;
        }
        return value;
    }
    public static void main(String[] args) {
        int a = bigger(2,5);
        //System.out.println(a);
        int b = smaller(6,8);
        //System.out.println(b);
        int value = clamp(0, 500, 255);
        System.out.println(value);
    }
}
