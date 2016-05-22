package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        BuffOn buffOn = new BuffOn(car);
        WaxOn waxOn = new WaxOn(car);

        ExecutorService service = Executors.newFixedThreadPool(10);



        service.execute(waxOn);
        service.execute(buffOn);

        TimeUnit.SECONDS.sleep(5);

        service.shutdownNow();
    }
}
