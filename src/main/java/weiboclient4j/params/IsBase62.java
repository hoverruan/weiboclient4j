package weiboclient4j.params;

import weiboclient4j.StatusService;

/**
 * @author Hover Ruan
 */
public enum IsBase62 implements
        StatusService.QueryIdParam,
        StatusService.QueryIdListParam {

    Yes(1), No(0);

    private int value;

    IsBase62(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        if (this == Yes) {
            params.add("isBase62", getValue());
        }
    }
}
