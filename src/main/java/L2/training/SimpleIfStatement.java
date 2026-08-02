package L2.training;

public class SimpleIfStatement {
    public static boolean isEven(int num) {
        return num%2==0;
    }
    public static boolean isYearLeap(int year){
        return year%4==0;
    }
    public static  int printMagnitude(int num){
        if (Math.abs(num)>1000000){
            System.out.println("large");



        } else if ((Math.abs(num))>100000&&Math.abs(num)<=1000000) {
            System.out.println("middle");


        } else{
            System.out.println("small");
        }
        return num;
    }
    public static int geartest(int num1,int num2,int num3) {
        if (num1 > num2 && num1 > num3) {
            return num1;

        } else if (num2 > num1 && num2 > num3) {
            return num2;
        } else {
            return num3;
        }



    }
    public static void main(String[] args) {
        System.out.println(isEven(5));
        System.out.println(isYearLeap(2000));
        System.out.println(geartest(3,4,7));
        System.out.println(printMagnitude(6777));
    }
}
