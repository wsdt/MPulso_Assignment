package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SerialNumberMalformedTest {
    @Test
    void throwException() {
        assertThrows(SerialNumberMalformed.class,()->{throw new SerialNumberMalformed("test successful");});
    }
}