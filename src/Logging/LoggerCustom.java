package Logging;

import java.io.IOException;
import java.util.logging.*;

public class LoggerCustom {
    Logger logger = Logger.getLogger("LoggerCustom");
    private static LoggerCustom  instance = new LoggerCustom();
    public static final String errorLog = "src/Logging/log.txt";
    public static final String warningLog = "src/Logging/warning.txt";
    public static final String fineLog = "src/Logging/fine.txt";

    private FileHandler logFile = null;
    private FileHandler fineFile = null;
    private FileHandler warningFile = null;

    public LoggerCustom(){

        try {
            logFile = new FileHandler(errorLog,true);
            warningFile = new FileHandler(warningLog , true);
            fineFile = new FileHandler(fineLog,true);

        }catch (SecurityException  se){
            se.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        logFile.setFormatter(new SimpleFormatter());
        warningFile.setFormatter(new SimpleFormatter());
        fineFile.setFormatter(new SimpleFormatter());

        logger.setLevel(Level.ALL);
        fineFile.setLevel(Level.FINE);
        warningFile.setLevel(Level.WARNING);

        logger.addHandler(logFile);
        logger.addHandler(warningFile);
        logger.addHandler(fineFile);
    }
    public static LoggerCustom getInstance(){
        return instance;
    }
    public void log(String msg){
        logger.finest(msg);
        logger.finer(msg);
        logger.fine(msg);
        logger.config(msg);
        logger.info(msg);
        logger.warning(msg);
        logger.severe(msg);
    }
    public  void fine(String msg){
        logger.fine(msg);
    }
    public void warning(String msg){
        logger.warning(msg);
    }
}
