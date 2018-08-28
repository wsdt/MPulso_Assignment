package exceptions;

/** Force application to crash (depends on use case) */
public class LoggerSetupFailed extends RuntimeException {
    public LoggerSetupFailed(String msg) {
        super(msg);
    }
}
