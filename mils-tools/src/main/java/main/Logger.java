package main;

import org.apache.logging.log4j.LogManager;

public final class Logger {
    private static org.apache.logging.log4j.Logger logger = LogManager.getRootLogger();

    public static org.apache.logging.log4j.Logger getInstance() {
        return logger;
    }

}
