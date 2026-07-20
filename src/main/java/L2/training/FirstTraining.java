package L2.training;

public class FirstTraining {
    public static void main(String[] args) {
        int birthYear = 2011;
        int age = 2026 - birthYear;
        int lifeDays = age * 365;
        int olderThenBugBy = lifeDays / 17;
        int missingDays = 17 - lifeDays % 17;
        System.out.println("We are sorry to say that "+olderThenBugBy+" bugs died");
    }
}
