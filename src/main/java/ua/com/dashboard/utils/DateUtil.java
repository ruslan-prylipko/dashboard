package ua.com.dashboard.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

    /**
     * Converts the date to corresponding input and output patterns.
     *
     * @param inputPattern pattern for input date.
     * @param outputPattern pattern to convert to.
     * @param dateFoConvert input date for convert.
     * @return {@code String} converted date.
     * @throws ParseException Signals that an error has been reached unexpectedly while parsing.
     */
    public static String convert(String inputPattern, String outputPattern, String dateFoConvert) throws ParseException {
        SimpleDateFormat inputDate = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputDate = new SimpleDateFormat(outputPattern);
        return outputDate.format(inputDate.parse(dateFoConvert));
    }
}
