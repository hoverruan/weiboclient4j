package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class StartTime extends LongParam {
    public StartTime(long value) {
        super(value);
    }

    protected String paramKey() {
        return "starttime";
    }
}
