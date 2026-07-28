package L2.training;

public class asdsa {
    public static int clamp(int max,int min,int num){
        if (num>max){
            return max;



        }
        else{
            if (num<min){
                return min;
            }
            else{
                return num;



            }

        }
    }
    public static void main(String[] args) {
        asdsa.clamp(200,0,6);
        System.out.println(asdsa.clamp(200,0,-6));

    }
}

