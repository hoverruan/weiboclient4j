package weiboclient4j;

import weiboclient4j.params.ParameterAction;
import weiboclient4j.params.Parameters;

/**
 * @author Hover Ruan
 */
public enum LocOffset implements ParameterAction{
    Original(0), Transformed(1);

    private int value;

    LocOffset(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        params.add("offset", getValue());
    }
}
