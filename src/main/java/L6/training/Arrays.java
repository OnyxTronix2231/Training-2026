package L6.training;

import com.fasterxml.jackson.databind.JsonSerializer;

public class Arrays {
    public static void array(){
        int[] array = new int[10];
        array[0] = 10;
        array[1] = array.length;
        array[9] = array[0] + array[1];
        if (array[1] > array[2]){
            array[2] = array[1] + 1;
        } else {
            array[2] -= 5;
        }

        while(array[3] <= array[2]){
            array[3] ++;
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        System.out.println(sum);
    }

    public static void array2(int[] array){
        int sum = 0;
        int highestSum = 0;
        int[] highestSumPlace = new int[2];
        for (int i = 0; i < array.length - 1; i++) {
            sum = array[i] + array[i + 1];
            if (sum > highestSum){
                highestSum = sum;
                highestSumPlace[0] = i;
                highestSumPlace[1] = i + 1;
            }
        }
        System.out.println(highestSum + " in places " + highestSumPlace[0] + ", " + highestSumPlace[1]);
    }

    public static int[] smallToBig(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] testArray = new int[]{-3, 11, 0, 5};
        array2(testArray);

        String sentence = "The quick brown fox jumps over the lazy dog";
        String[] words = sentence.split(" ");
        String newSentence = "";
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
            newSentence += words[i] + " ";
        }
        System.out.println(newSentence);

        int[] newArray = smallToBig(testArray);
        for (int i = 0; i < testArray.length; i++) {
            System.out.print(newArray[i] + ", ");
        }
    }
}
