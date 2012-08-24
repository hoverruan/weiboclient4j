package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum SuggestionRange implements ParameterAction {
    Name(0), Remark(1), All(2);

    private int value;

    SuggestionRange(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        if (this != All) {
            params.add("range", getValue());
        }
    }
}
