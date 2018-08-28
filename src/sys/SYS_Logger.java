package sys;

import com.sun.istack.internal.NotNull;
import exceptions.LoggerSetupFailed;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Own custom logger, to log on console and into file simultaneously without needing two
 * statements every time.
 */
public class SYS_Logger {
    private static boolean isSetup = false;
    private static Logger logger = Logger.getLogger("MPulso_Execution");

    private SYS_Logger() {
    }

    private static void setup() {
        System.out.println("SYS_Logger:constr: Starting sysLogger configuration.");
        try {
            GregorianCalendar gc = new GregorianCalendar();
            String logFileName = gc.get(GregorianCalendar.DAY_OF_MONTH)+"-"+gc.get(GregorianCalendar.MONTH)+"-"+gc.get(GregorianCalendar.YEAR)+"_"+gc.get(GregorianCalendar.HOUR_OF_DAY)+"-"+gc.get(GregorianCalendar.MINUTE)+"-"+gc.get(GregorianCalendar.SECOND)+"-"+gc.get(GregorianCalendar.MILLISECOND);
            FileHandler fh = new FileHandler("./logs/"+logFileName+".log");
            logger.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());

            logger.info("SYS_Logger is ready.");
            isSetup = true;
        } catch (SecurityException | IOException e) {
            System.err.print("SYS_Logger:constr: Could not initialize logger. See stacktrace.");
            e.printStackTrace();
            throw new LoggerSetupFailed("Could not setup logger, please see other error msgs.");
        }
    }


    //GETTER/SETTER +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static Logger getLogger() {
        if (!isSetup) {
            setup();
        }
        return logger;
    }

    protected static void setLogger(@NotNull Logger logger) {
        SYS_Logger.logger = logger;
    }
}
