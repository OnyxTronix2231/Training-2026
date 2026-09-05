package L8.training;

import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) {
        UnizRobot unizRobot = new UnizRobot();
        for (int i = 0; i < 1500; i++) {
            unizRobot.periodic();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
