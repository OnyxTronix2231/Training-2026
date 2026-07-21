package L2.training;

public class training {




    public static boolean the(boolean num1){
        num1=!num1;
        return num1;


    }
    public static int add(int num1, int num2){
        int sum=num1+num2;
        return sum;

    }
    public static boolean printstate(int num1,int num2){
        boolean get=num1>num2;
        return get;
    }
    public static double divine(double num3,double num4){
        double result=num3/num4;
        return result;


    }
    public static void main(String[] args) {

        int sum = training.add(1,2);
        System.out.println(sum);
        double result=training.divine(6,5);
        System.out.println(result);
        boolean get=training.printstate(6,7);
        System.out.println(get);
        boolean result1=training.the(true);
        System.out.println(result1);



    }
}
