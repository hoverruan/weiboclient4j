package weiboclient4j.utils;

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
}
