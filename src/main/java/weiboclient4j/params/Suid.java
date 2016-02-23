package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Suid extends LongParam {
    public static final Suid EMPTY = new Suid(0);

    public Suid(long value) {
        super(value);
    }

    public Suid(String value) {
        super(value);
    }

    protected String paramKey() {
        return "suid";
    }
}
