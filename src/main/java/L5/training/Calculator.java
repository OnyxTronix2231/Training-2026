package L5.training;

public class Calculator {
    public static int factorial(int number){
        int result = 1;
        int num=number;
        for (int i = 1; i <= number ; i++) {
          result *= i;


        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(factorial(12));
    }
}
