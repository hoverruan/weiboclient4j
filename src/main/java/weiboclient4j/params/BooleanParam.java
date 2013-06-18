package weiboclient4j.params;

public abstract class BooleanParam implements ParameterAction {
    private boolean value;

    public BooleanParam(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        params.add(paramKey(), value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    protected abstract String paramKey();
}
