package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class TrendId extends LongParam {
    public TrendId(long value) {
        super(value);
    }

    protected String paramKey() {
        return "trend_id";
    }
}
