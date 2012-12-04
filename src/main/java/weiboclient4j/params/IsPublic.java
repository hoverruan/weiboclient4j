package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum IsPublic implements ParameterAction {
    Yes(1), No(0);

    private int value;

    IsPublic(int value) {
        this.value = value;
    }

    public void addParameter(Parameters params) {
        params.add("public", value);
    }
}
