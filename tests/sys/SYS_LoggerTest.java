package sys;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SYS_LoggerTest {
    @Test
    void logWarn() {
        SYS_Logger.getLogger().warning("Test");
    }

    @Test
    void logSevere() {
        SYS_Logger.getLogger().severe("Test");
    }

    @Test
    void logFine() {
        SYS_Logger.getLogger().fine("Test");
    }

    @Test
    void logInfo() {
        SYS_Logger.getLogger().info("Test");
    }
}