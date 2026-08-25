package L6.training;

public class Arrays {
    public static void main(String[] args) {
        int[] intArrayBiggerTwo = new int[8];
        intArrayBiggerTwo[5] = 5;
        intArrayBiggerTwo[6] = 2;
        int biggestDwo = 0;
        int index1 = 0;
        int index2 = 0;
        theBiggestTwo(intArrayBiggerTwo, biggestDwo, index1, index2);



        String sentence = "The quick brown fox jumps over the lazy dog";
        String[] stringArray = new String[9];
        String[] words = sentence.split(" ");
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = words[i];
            System.out.println(stringArray[i]);
        }
        String recombinedString = "";
        for (int i = 0; i < stringArray.length; i++) {
            recombinedString += stringArray[i] + " ";
        }
        System.out.println(recombinedString);



        int[] intArray = new int[10];
        intArray[0] = 10;
        intArray[1] = intArray.length;
        intArray[intArray.length - 1] = intArray[0] + intArray[1];
        if (intArray[1] > intArray[2]) {
            intArray[2] = intArray[1] + 1;
        } else {
            intArray[2] -= 5;
        }
        while (intArray[3] <= intArray[2]) {
            intArray[3]++;
        }
        int total = 0;
        for (int i = 0; i < intArray.length; i++) {
            total += intArray[i];
        }
        //System.out.println(total);
    }

    public static void theBiggestTwo(int[] intArrayBiggerTwo,int biggestDwo, int index1, int index2) {
        for (int i = 0; i < intArrayBiggerTwo.length - 1; i++) {
            int sum = 0;
            sum = intArrayBiggerTwo[i] + intArrayBiggerTwo[i + 1];
            if (sum > biggestDwo) {
                biggestDwo = sum;
                index1 = i;
                index2 = i + 1;
            }
        }
        String string = "The biggest sum is " + biggestDwo;
        System.out.println(string);
        System.out.println(index1);
        System.out.println(index2);
    }
}
