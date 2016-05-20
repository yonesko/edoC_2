package gleb;

public class Util {
    public static void log() {
        StackTraceElement x = Thread.currentThread().getStackTrace()[2];
        System.out.println(x.getClassName() + ":" + x.getMethodName());
    }

    public static void log(String msg) {
        StackTraceElement x = Thread.currentThread().getStackTrace()[2];
        System.out.println(x.getClassName() + ":" + x.getMethodName() + ":" + msg);
    }
}
