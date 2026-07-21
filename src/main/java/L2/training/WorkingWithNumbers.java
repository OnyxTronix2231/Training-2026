package L2.training;

public class WorkingWithNumbers {

    public static void add(int a, int b) {
        int c = a + b;
        System.out.println(c);
    }

    public static void divide(double d, double e) {
        double f = d / e;
        System.out.println(f);
    }

    public static int schom(int g, int h, int i) {
        int j = g + h + i;
        return j;
    }

    public static int fakeAverage(int k, int l) {
        int m = k / l;
        return m;
    }

    public static double realAverage(double n, double o) {
        double p = n / o;
        return p;
    }

    public static void printState(boolean q) {
        System.out.println(q);
    }

    public static void flippedState(boolean r) {
        r = !r;
        System.out.println(r);
    }

    public static void main(String[] args) {
        add(5, 5);
        divide(10, 5);
        int j = schom(1, 2, 3);
        int m = fakeAverage(10, 4);
        double p = realAverage(10, 4);
        printState(true);
        flippedState(true);

    }


}
