package L8.training;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        RealRobot robot = new RealRobot();
        for (int i = 0; i < 24.9; i++) {
            robot.periodic();
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
