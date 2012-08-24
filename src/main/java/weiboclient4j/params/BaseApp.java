package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum BaseApp implements ParameterAction {
    Yes(1), No(0);

    private int value;

    BaseApp(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BaseApp fromValue(int value) {
        if (value == Yes.getValue()) {
            return Yes;
        } else if (value == No.getValue()) {
            return No;
        }

        return null;
    }

    public void addParameter(Parameters params) {
        if (this == Yes) {
            params.add("base_app", getValue());
        }
    }
}
