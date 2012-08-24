package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum IsPic implements ParameterAction {
    All(0), Pic(1);

    private int value;

    IsPic(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        params.add("is_pic", getValue());
    }
}
