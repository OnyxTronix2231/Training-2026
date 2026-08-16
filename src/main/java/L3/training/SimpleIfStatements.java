package L3.training;

public class SimpleIfStatements {
    public static boolean isEven(int num) {
        return num%2==0;
    }
    public static boolean isLeapYear(int year) {
        return year % 4 == 0;
    }
    public static int getGreatest(int num1, int num2, int num3) {
        int greater = Math.max(num1,num2);
        return Math.max(greater,num3);
    }
    public static void printMagnitude (int num){
        if (num<100000)
            System.out.println("Small");
        if (num>1000000)
            System.out.println("Large");
        if (num>100000&&num<1000000)
            System.out.println("Middle");

    }
    public void isGreatNumbers(int num1,int num2) {
        if (num1%2==0&&num1>num2)
            System.out.println("Great Numbers");
        else if (num2%2==0&&num1>num2)
            System.out.println("Nice Numbers");
        else
            System.out.println("OK Numbers");
    }
}
