package L6.training;

public class Array2 {
    public static void main(String[] args) {
        String s = "The quick brown fox jumps over thr lazy dog ";
            String[] arr=s.split(" ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);

        }
        String back="";
        for (int i = 0; i < arr.length; i++) {
            back=back+arr[i]+" ";

        }
        System.out.println("\n"+back);


    }


}

