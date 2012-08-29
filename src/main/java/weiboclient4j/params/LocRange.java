package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class LocRange extends LongParam {
    public LocRange(long value) {
        super(value);
    }

    protected String paramKey() {
        return "range";
    }
}
