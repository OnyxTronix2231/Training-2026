package L5.training;

public class calculator {
    public static int factorial(int number){
        int result=1;
        while (number>0){
            result = result * number;
            number = number-1;
        }
        return  result;

    }
    public static int fac(int num){
        int result2=1;
        for (int i = 0; num>0 ; i++) {
            result2= result2*num;
            num=num-1;


        }
        return result2;
        }

    public static void main(String[] args) {
        System.out.println(factorial(12));
        System.out.println(fac(12));
    }

}
