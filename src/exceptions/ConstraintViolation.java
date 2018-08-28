package exceptions;

public class ConstraintViolation extends RuntimeException {
    public ConstraintViolation(String msg) {
        super(msg);
    }
}
