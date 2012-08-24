package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum SuggestionStatusType implements ParameterAction {
    Ent(1), Joke(2), Belle(3), Video(4), Constellation(5), Cute(6), Fashion(7), Car(8), Food(9), Music(10);

    private int value;

    SuggestionStatusType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        params.add("type", getValue());
    }
}
