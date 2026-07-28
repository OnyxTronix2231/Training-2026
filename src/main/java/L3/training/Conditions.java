package L3.training;

public class Conditions {
    public static void bigOrSmall(int a){
        if (a > 5){
            System.out.println("Big");
        }
      else{
            System.out.println("Small");
        }
    }

    public static void main(String[] args) {
        bigOrSmall(6);
    }
}
