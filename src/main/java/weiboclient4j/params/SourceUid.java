package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class SourceUid extends LongParam {
    public static final SourceUid EMPTY = new SourceUid(0);

    public SourceUid(long value) {
        super(value);
    }

    public SourceUid(String value) {
        this(Long.parseLong(value));
    }

    protected String paramKey() {
        return "source_id";
    }
}
