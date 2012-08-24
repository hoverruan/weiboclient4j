package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum WithoutMention implements ParameterAction {
    Yes(1), No(0);

    private int value;

    WithoutMention(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        if (this == Yes) {
            params.add("without_mention", getValue());
        }
    }
}
