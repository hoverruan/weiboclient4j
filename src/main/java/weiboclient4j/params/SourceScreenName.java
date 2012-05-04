package weiboclient4j.params;

import static weiboclient4j.utils.StringUtils.isNotBlank;

/**
 * @author Hover Ruan
 */
public class SourceScreenName {
    public static final SourceScreenName EMPTY = new SourceScreenName(null);

    private String value;

    public SourceScreenName(String value) {
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
