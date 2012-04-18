package weiboclient4j.utils;

import java.util.Collection;

/**
 * @author Hover Ruan
 */
public class StringUtils {
    public static boolean isBlank(String value) {
        return !isNotBlank(value);
    }

    public static boolean isNotBlank(String value) {
        return value != null && value.trim().length() > 0;
    }

    public static String join(Collection<String> strings, String sep) {
        StringBuilder buf = new StringBuilder();
        for (String str : strings) {
            buf.append(sep).append(str);
        }
        return buf.substring(sep.length());
    }
}
