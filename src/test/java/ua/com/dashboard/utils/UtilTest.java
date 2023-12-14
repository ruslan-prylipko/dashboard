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

    @Test
    void stringValidateCorrectParameters() {
        assertTrue(Util.stringValidate("Hello"));
        assertTrue(Util.stringValidate("Hello", "World", "!!!"));
    }

    @Test
    void stringValidateIncorrectParameters() {
        assertThrows(IllegalArgumentException.class, () -> Util.stringValidate(""));
        assertThrows(IllegalArgumentException.class, () -> Util.stringValidate("    "));
        assertThrows(NullPointerException.class, () -> Util.stringValidate("Hello", null));
        assertThrows(NullPointerException.class, () -> Util.stringValidate(null));
    }
}