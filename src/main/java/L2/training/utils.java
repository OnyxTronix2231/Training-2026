package L2.training;

public class utils {
    public static void min (int number3,int number4){
        if (number3>number4) {
            System.out.println(number4 + "is smaller");
        }else{
            System.out.println(number3 + "is smaller");
        }
    }
    public static void max (int number1,int number2){
        if (number1>number2) {
            System.out.println(number1 + "is bigger");
        }else{
            System.out.println(number2 + "is bigger");
        }
    }

    public static int  clamp (int min, int max, int normal){
        if (normal>max){
            return max;
        }if (normal<min){
            return min;
        }
        return normal ;
    }

    public static void main(String[] args) {
        System.out.println(clamp(0,255,-6));
    }
}
