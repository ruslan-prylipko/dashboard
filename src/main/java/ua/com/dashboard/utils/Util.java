package ua.com.dashboard.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static boolean isMatchingToPattern(String pattern, String toMatching) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(toMatching);
        boolean b = m.matches();
        if (!b) {
            throw new IllegalArgumentException(toMatching + " doesn't match to pattern " + pattern);
        }
        return true;
    }
}
