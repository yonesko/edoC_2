package main;

import java.util.concurrent.TimeUnit;

/**
 * Created by gleb on 22.05.16.
 */
public class BuffOn implements Runnable {
    private Car car;

    public BuffOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.buff();
                TimeUnit.MILLISECONDS.sleep(200);
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End of BuffOn");
    }
}
