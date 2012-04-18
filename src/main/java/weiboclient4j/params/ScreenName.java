package weiboclient4j.params;

import static weiboclient4j.utils.StringUtils.isNotBlank;

/**
 * @author Hover Ruan
 */
public class ScreenName {
    public static final ScreenName EMPTY = new ScreenName(null);

    private String value;

    public ScreenName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public boolean isValid() {
        return isNotBlank(value);
    }
}
