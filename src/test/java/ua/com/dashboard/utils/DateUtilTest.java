package ua.com.dashboard.utils;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @SneakyThrows
    @Test
    void testConvertCorrectInput() {
        assertEquals("10-11-2023",
                DateUtil.convert("yyyy-MM-dd", "dd-MM-yyyy", "2023-11-10"));
    }

    @SneakyThrows
    @Test
    void testConvertInCorrectInput() {
        assertNotEquals("10-11-2023",
                DateUtil.convert("yyyy-MM-dd", "dd-MM-yyyy", "10-11-2023"));
    }

    @Test
    void testConvertInCorrectInput1() {
        assertThrows(ParseException.class,
                () -> DateUtil.convert("yyyy-MM-dd", "dd-MM-yyyy", "10.11.2023"));
    }
}