package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public abstract class FloatParam implements ParameterAction {
    private float value;

    public FloatParam(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    protected abstract String paramKey();

    public void addParameter(Parameters params) {
        params.add(paramKey(), getValue());
    }
}
