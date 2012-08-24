package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Tid extends LongParam {
    public Tid(long value) {
        super(value);
    }

    protected String paramKey() {
        return "tid";
    }
}
