package ua.com.dashboard.utils;

import lombok.extern.log4j.Log4j2;
import ua.com.dashboard.view.user.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class Util {

    /**
     * Validates {@code toMatching} is according to {@code pattern}
     *
     * @param pattern pattern for checking
     * @param toMatching parameter for checking
     * @return {@code true} if validate is ok
     * @throws IllegalArgumentException
     */
    public static boolean isMatchingToPattern(String pattern, String toMatching) {
        String errorMessage = toMatching + " doesn't match to pattern " + pattern;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(toMatching);
        boolean b = m.matches();
        if (!b) {
            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return true;
    }

    /**
     * Validates is strings empty or blank.
     *
     * @param args strings array
     * @return {@code true} if string is not empty or blank
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public static boolean stringValidate(String ... args) {
        String errorMessage = "Incorrect parameter.";
        if (args == null) {
            log.error(errorMessage);
            throw new NullPointerException(errorMessage);
        }
        for (String str: args) {
            if (str == null) {
                log.error(errorMessage);
                throw new NullPointerException(errorMessage);
            }
            if (str.isEmpty() || str.isBlank()) {
                log.error(errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }
        }
        return true;
    }
}
