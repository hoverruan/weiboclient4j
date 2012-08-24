package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum SuggestionType implements ParameterAction {
    Friends(0), Followers(1);

    private int value;

    SuggestionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        params.add("type", getValue());
    }
}
