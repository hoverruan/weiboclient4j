package weiboclient4j.params;

import static weiboclient4j.utils.StringUtils.isNotBlank;

/**
 * @author Hover Ruan
 */
public class TargetScreenName {
    public static final TargetScreenName EMPTY = new TargetScreenName(null);

    private String value;

    public TargetScreenName(String value) {
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
