package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Latitude extends FloatParam {
    public Latitude(float value) {
        super(value);
    }

    protected String paramKey() {
        return "lat";
    }
}
