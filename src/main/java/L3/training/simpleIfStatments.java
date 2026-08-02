package L3.training;

public class simpleIfStatments {
    public static boolean isEven(int number) {
        if (number%2==0) {
            return true;
        } else {
            return false;
        }
    }

    public static String isLeapYear(int year) {
        if (year%4==0) {
            return "שנה מעוברת";
        } else {
            return "לא שנה מעוברת";
        }
    }
    public static int getGreatest(int num1, int num2, int num3) {
        if (num1 > num2 && num1 > num3) {
                return num1;
        } else {
            if (num2 > num3) {
                return num2;
            }
        }
        return num3;
    }

    public static int absoluteValue(int number) {
        if (number < 0) {
            return number = -number;
        }
        return number;
    }

    public static String printMagnitude(int number) {
        if (absoluteValue(number) >= 1000000) {
            return "large";
        }
        if (absoluteValue(number) > 100000 && absoluteValue(number) < 1000000) {
            return "middle";
        }
        return "small";
    }

    public static void getDay(int today) {
        if (today == 1) {
            System.out.println("sunday");
        }
        if (today == 2) {
            System.out.println("monday");
        }
        if (today == 3) {
            System.out.println("thusday");
        }
        if (today == 4) {
            System.out.println("wedensday");
        }
        if (today == 5) {
            System.out.println("thursday");
        }
        if (today == 6) {
            System.out.println("friday");
        }
        if (today == 7) {
            System.out.println("saturday");
        }
    }

    public static void isGreatNumbers(int num1, int num2) {
        if (num1 > num2 && num1%2 == 0) {
            System.out.println("Great Numbers");
        } else if (num2 > num1 && num2%2 == 1) {
            System.out.println("Nice Numbers");
        } else {
            System.out.println("OK Numbers");
        }
    }

    public static boolean AreFunnyNumbers(int num1, int num2) {
        return ((num1*num1 != num2*num2) && ((num1 > 0 && num2 > 0) || (num1 < 0 && num2 < 0)));
    }

    public static void main(String[] args) {
        boolean a = simpleIfStatments.isEven(12);
        String b = simpleIfStatments.isLeapYear(2026);
        int c = simpleIfStatments.getGreatest(2, 5, 12);
        String d = simpleIfStatments.printMagnitude(1000000);
        System.out.println(d);
        int today = 5;
        getDay(today);
        simpleIfStatments.isGreatNumbers(6,4);
        boolean e = simpleIfStatments.AreFunnyNumbers(6,4);
        System.out.println(e);
    }
}
