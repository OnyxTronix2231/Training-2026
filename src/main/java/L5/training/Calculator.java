package L5.training;

public class Calculator {
    public static int factorial(int number) {
        int result = 1;
        while (number > 1) {
            result = result * number;
            number --;
        }
        return result;
    }
    public static void factorialFor(int number) {
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result = i * result;
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        int a = factorial(12);
        System.out.println(a);
        factorialFor(6);
    }
}
