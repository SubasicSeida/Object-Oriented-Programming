package Week13;

class Logger {
    private static Logger instance;

    public Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("LOG: " + message);
    }

    public void logInfo(String message) {
        System.out.println("INFO: " + message);
    }

    public void logWarning(String message) {
        System.out.println("WARNING: " + message);
    }

    public void logError(String message) {
        System.err.println("ERROR: " + message);
    }
}

class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.log("Application started.");
        logger.logInfo("Initializing components");
        logger.logWarning("Low memory detected.");
        logger.logError("Failed to connect to the database.");
        logger.log("Application shutting down.");
    }
}