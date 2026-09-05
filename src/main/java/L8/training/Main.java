package L8.training;

import java.util.concurrent.TimeUnit;

public class Main {
    public static final int x = 1500;
    public static void main(String[] args) throws InterruptedException {
        Roobot robot = new Roobot();
        for (int i = 0; i < x; i++) {
            robot.periodic();
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}