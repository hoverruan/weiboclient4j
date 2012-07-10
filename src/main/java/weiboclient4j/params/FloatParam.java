package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class FloatParam {
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
}
