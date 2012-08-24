package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class TrendName extends StringParam {
    public TrendName(String value) {
        super(value);
    }

    protected String paramKey() {
        return "trend_name";
    }
}
