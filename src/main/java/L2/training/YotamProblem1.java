package L2.training;

public class YotamProblem1 {
    public static void main(String[] args) {
        int birthYear = 2011;
        int currentYear = 2026;
        int ageNow = currentYear - birthYear;
        System.out.println(ageNow);
        double yearDay= ageNow*365;
        int dayYear = ageNow*365;
        System.out.println(yearDay);
        double flyMogged = yearDay/17;
        System.out.println(flyMogged);
        double dayLeft = dayYear%17;
        double leftDay = 17 - dayLeft;
        System.out.println(leftDay);
        int flyDie = dayYear / 17;
        System.out.println(flyDie);




    }
}
