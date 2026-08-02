package L3.training;

public class SimpleIfStatements {
    public static boolean isEven(int number) {
        if (number % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
    public static void isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            System.out.println("Is Leap Year"); ;
        }
        else  {
            System.out.println("Is Not Leap Year");;
        }
    }
    public static int getGreatest(int number1, int number2, int number3) {
    if(number1 > number2 && number1 > number3) {
        return number1;
    }
    else if (number2 > number1 && number2 > number3) {
        return number2;
    }
    else{
        return number3;}
    }
    public static void printMagnitude(int magnitude) {
        if (magnitude < 0) {
            magnitude = magnitude * -1;;
        }
        
        }
    }


