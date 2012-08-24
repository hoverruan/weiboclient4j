package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Num extends LongParam {
    public static final Num EMPTY = new Num(0);

    public Num(long value) {
        super(value);
    }

    protected String paramKey() {
        return "num";
    }
}
