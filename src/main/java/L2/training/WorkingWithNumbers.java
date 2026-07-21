package L2.training;

public class WorkingWithNumbers {
    public static void main(String[] args) {

    }

    public static void addNumbers (int num1, int num2){
        System.out.println(num1+num2);
    }

    public static void divideNumbers (double num1, double num2){
        System.out.println(num1/num2);
    }

    public static double addThreeNumbers (double num1, double num2, double num3){
        return num1+num2+num3;
    }

    public static int fakeAverage (int num1, int num2){
        return (num1+num2)/2;
    }

    public static double realAverage (double num1, double num2){
        return (num1+num2)/2;
    }

    public static void printState (boolean bol){
        System.out.println(bol);
    }

    public static void flippedState (boolean bol){
        System.out.println(!bol);
    }
}
