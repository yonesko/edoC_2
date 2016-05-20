package gleb.devices;

public class Light {
    public void on() {
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

    }

    public void off() {
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

    }
}
