package L6.training;

public class Sorting {
    public static void main(String[] args) {
        int[] numbers = new int[] {8, 6, 4, 2, 5};
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        for (int k = 0; k < numbers.length; k++) {
            System.out.println(numbers[k]);
        }
    }
}
