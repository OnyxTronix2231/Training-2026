package L3.training;

public class Utils {

    public int max (int number1, int number2)
    {
        if (number1 > number2)
            return number1;
        else
            return number2;
    }
    public int min (int number1, int number2) {
        if (number1 > number2)
            return number2;
        else
            return number1;
    }

    public int clamp (int value, int lowerBound, int upperBound) {
        if (value > upperBound)
            return upperBound;
        if (value < lowerBound)
            return lowerBound;
        return value;
    }
}