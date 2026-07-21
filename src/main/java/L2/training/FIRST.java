package L2.training;

public class FIRST {
    public static void main(String[] args) {
        add(1, 2);
        divide(4,6);
        apple(1,2,3);
        fakeAverage(10,5);
        realAverage(3,5);


    }

    public static void add(int num1, int num2 ){
        double num3 = num1 + num2;
        System.out.println(num3);

    }

    public static void divide (double num4 ,double num5 ){
        System.out.println(num4/num5);

    }
    public static double apple(double num6,double num7,double num8){
        double num9 = num6+num7+num8;
        System.out.println(num9);
        return num9;
    }
    public static int fakeAverage(int number, int number1){
        int number2= number + number1;
        int number3 =number2/2;
        return number2;
    }
    public static double realAverage(double a ,double b){
        double c= a/b;
        return c;
    }
    public static void  printstate(boolean q){
        System.out.println(q);
    }
    public static boolean flippedState(boolean w) {
        System.out.println(false);
        return w;
    }

}
