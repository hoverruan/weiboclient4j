package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class EndTime extends LongParam {
    public EndTime(long value) {
        super(value);
    }

    protected String paramKey() {
        return "endtime";
    }
}
