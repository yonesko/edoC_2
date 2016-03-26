package gleb.server;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoGGer {
    private static Logger logger;
    private static FileHandler fh;

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void warning(String msg) {
        logger.warning(msg);
    }

    public static void log(Level level, String msg, Throwable thrown) {
        logger.log(level, msg, thrown);
    }
    public static void printException(Throwable thrown) {
        logger.log(Level.SEVERE, null, thrown);
    }

    public static void severe(String msg) {
        logger.severe(msg);
    }

    static {
        logger = Logger.getLogger("Log");
        try {
            fh = new FileHandler("Log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        logger.addHandler(fh);
    }
}
