package weiboclient4j.params;

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
