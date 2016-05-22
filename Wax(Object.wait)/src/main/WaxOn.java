package main;

import java.util.concurrent.TimeUnit;

/**
 * Created by gleb on 22.05.16.
 */
public class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.wax();
                TimeUnit.MILLISECONDS.sleep(400);
                car.waitForWaxing();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End of WaxOn");
    }
}
