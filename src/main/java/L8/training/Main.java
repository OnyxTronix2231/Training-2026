package L8.training;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Roobot roobot = new Roobot();
        for (int i = 0; i <1501 ; i++) {
            roobot.Periodic();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
