package ua.com.dashboard.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void isMatchingToPatternCorrectInput() {
        assertTrue(Util.isMatchingToPattern("\\d{2}-\\d{2}-\\d{4}", "15-11-2023"));
    }

    @Test
    void isMatchingToPatternInCorrectInput() {
        assertThrows(IllegalArgumentException.class,
                () -> Util.isMatchingToPattern("\\d{2}-\\d{2}-\\d{4}", "2023-11-15"));
    }
}