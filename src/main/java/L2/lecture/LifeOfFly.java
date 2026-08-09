package L2.lecture;

public class LifeOfFly {
    public static void main(String[] args) {
        int yearOfBirth = 2004;
        int age = 2026 - yearOfBirth;
        System.out.println(age);
        int daysInLife = age * 365;
        System.out.println(daysInLife);
        int flyAvgAge = 17;
        double fliesLived = daysInLife / flyAvgAge;
        System.out.println(fliesLived);
        int remainingDays = flyAvgAge - daysInLife % flyAvgAge;
        System.out.println(remainingDays);

        System.out.println("In great sorrow we announce the death of " + fliesLived + " flies.");
    }
}
