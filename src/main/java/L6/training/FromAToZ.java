package L6.training;

public class FromAToZ {
    public static void main(String[] args) {
        String allLetters= "The quick brown fox jumps over the lazy dog";
        String[] Sentence= new String[9];
        Sentence = allLetters.split(" ");
        for (int i = 0; i <Sentence.length ; i++) {
            System.out.print(Sentence[i]+" ");
        }
        String backToSentence = "";
        for (int j = 0; j < Sentence.length; j++) {
            backToSentence += Sentence[j]+" ";
        }
        System.out.println("\n"+backToSentence);
    } 
}
