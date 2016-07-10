package main;

import java.time.Instant;
import java.util.Random;

/**
 * Created by gleb on 10 July 2016.
 */
public class Model {
    public static String getTime() {
        return Instant.now().toString();
    }
    public static int getNum() {
        return new Random().nextInt();
    }
}
