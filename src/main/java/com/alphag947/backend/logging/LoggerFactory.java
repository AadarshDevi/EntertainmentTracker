package com.alphag947.backend.logging;

public class LoggerFactory {
    private static ConsoleLogger cl;
    private static Logger logger;

    public static ConsoleLogger getConsoleLogger() {
        if (cl == null)
            cl = new ConsoleLogger();
        return cl;
    }

    public static Logger getLogger() {
        if (logger == null)
            logger = new Logger();

        return logger;
    }
}
