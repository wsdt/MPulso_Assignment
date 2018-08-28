package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstraintViolationTest {
    @Test
    void throwException() {
        assertThrows(ConstraintViolation.class, ()->{throw new ConstraintViolation("test successful");});
    }
}