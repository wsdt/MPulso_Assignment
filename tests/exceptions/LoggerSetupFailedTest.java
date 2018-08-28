package exceptions;


import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by IntelliJ IDEA.
 * User: kevin
 * Date: 28.08.2018
 * Time: 05:28
 */
class LoggerSetupFailedTest {
    @Test
    public void throwException() {
        Executable exc = () -> throw new LoggerSetupFailed("test successful");
        assertThrows(LoggerSetupFailed.class, )
    }
}