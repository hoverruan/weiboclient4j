package weiboclient4j.params;

import weiboclient4j.utils.StringUtils;

/**
 * @author Hover Ruan
 */
public class TagName {
    private String value;

    public TagName(String value) {
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
        return StringUtils.isNotBlank(value);
    }
}
