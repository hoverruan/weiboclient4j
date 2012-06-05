package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Uid extends LongParam {
    public static final Uid EMPTY = new Uid(0);

    public Uid(long value) {
        super(value);
    }
}
