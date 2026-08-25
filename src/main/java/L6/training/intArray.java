package L6.training;

public class intArray {
    public static void main(String[] args) {

            String line ="The quick brown fox jumps over the lazy dog";
            String[] words=line.split(" ");
        System.out.println(words[0]);
        String[] line2 = new String[9];
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            System.out.print(words[i]+ " ");
        }
        theBigger(new int[]{1,2,3,4});
        int[] array = new int [10];
        array[0]=10;
        array[2]=array.length;
        array[9] =array[1]+array[2];
        if (array[2]>array[1]){
            array[2]=array[1] + 1;
        }else{
            array[2]-=5;
        }
        while (array[3]<=array[2]){
            array[3]++;
        }

        int sum =0;
        for (int i = 0; i <array.length ; i++) {
            sum+= array[i];
        }
        System.out.println(sum);
    }


    //---------------------------------------------------------------

    public static void theBigger (int[]arr){
        int sum =0;
        int maxsum=arr[0]+arr[1];
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i]+arr[i+1]>maxsum){
                maxsum = arr[i]+arr[i+1];
            }
        }System.out.println(maxsum);

    }

}
