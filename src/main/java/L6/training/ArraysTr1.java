package L6.training;

public class ArraysTr1 {
    public static void sumBeside(int[] array) {
        int maxSum = 0;
        int maxindex1= 0;
        int maxindex2= 0;
        for (int i = 0; i < array.length-1; i++) {
            if (maxSum < array[i]+array[i+1]) {
                maxSum = array[i]+array[i+1];
            }
            maxSum=maxSum;
            maxindex1=i;
            maxindex2=i+1;
        }
        System.out.println(maxSum);
        System.out.println(maxindex1);
        System.out.println(maxindex2);
    }

    public static void main(String[] args) {
        sumBeside(new int[]{1,2,3,4,5});
    }
}

