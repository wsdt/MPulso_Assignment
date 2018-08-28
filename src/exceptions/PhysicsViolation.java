package exceptions;

public class PhysicsViolation extends RuntimeException {
    public PhysicsViolation(String msg) {
        super(msg);
    }
}
