package L6.training;

public class Array {
    public static int Array2(int[] arr){
        int max=0;
        for (int i = 0; i < arr.length-1; i += 1) {
            if(max<arr[i]+arr[i+1]){
                max=arr[i]+arr[i+1];
            }else{
                max=max;
            }

        }


        return max;
    }
    public static void main(String[] args) {

        System.out.println(Array.Array2(new int[]{1,2,3,4}));
    }

}




