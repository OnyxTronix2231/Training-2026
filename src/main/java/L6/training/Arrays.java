package L6.training;

public class Arrays {
    public static void main(String[] args) {
        int sum2=0;
        int[] array = new int[10];
        array[0] += 10;
        int length = array.length;
        array[1] += length;
        int sum = array[0] + array[1];
        array[length-1] += sum;
        if (array[1]>array[2]) {
            array[2] += array[1]+1;
        }
        array[2] -= 5;
        while(array[3]<array[2]) {
            array[3] ++;
        }
        for (int i = 0; i < length; i++) {
            sum2 += array[i];
            System.out.println(sum2);
        }

    }

}
