package L5.training;

public class Calculator {
    public static int factorial(int number) {
        int result =1;
        for (int i = number; i>0; i--) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(factorial(12));
    }
}
