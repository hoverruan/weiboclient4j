package weiboclient4j.params;

import static weiboclient4j.utils.StringUtils.isNotBlank;

/**
 * @author Hover Ruan
 */
public class Mid {
    private String value;

    public Mid(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean isValid() {
        return isNotBlank(value);
    }
}
