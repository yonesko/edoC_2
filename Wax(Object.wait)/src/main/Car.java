package main;

/**
 * Created by gleb on 22.05.16.
 */
public class Car {
    private boolean isWaxed;

    public synchronized void wax() {
        isWaxed = true;
        System.out.println(isWaxed);
        notifyAll();
    }

    public synchronized void buff() {
        isWaxed = false;
        System.out.println(isWaxed);
        notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (isWaxed == true) {
            System.out.println(Thread.currentThread() + " " + isWaxed + " wait");
            wait();
        }
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (isWaxed == false) {
            System.out.println(Thread.currentThread() + " " + isWaxed + " wait");
            wait();
        }
    }

}
