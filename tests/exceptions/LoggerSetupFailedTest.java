package exceptions;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class LoggerSetupFailedTest {
    @Test
    void throwException() {
        Assertions.assertThrows(LoggerSetupFailed.class, () -> {throw new LoggerSetupFailed("test successful.");});
    }


}