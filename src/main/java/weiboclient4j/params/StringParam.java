package weiboclient4j.params;

import weiboclient4j.utils.StringUtils;

/**
 * @author Hover Ruan
 */
public class StringParam {
    private String value;

    public StringParam(String value) {
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
