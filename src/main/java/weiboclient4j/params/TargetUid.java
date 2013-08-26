package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class TargetUid extends LongParam {
    public static final TargetUid EMPTY = new TargetUid(0);

    public TargetUid(long value) {
        super(value);
    }

    public TargetUid(String value) {
        this(Long.parseLong(value));
    }

    protected String paramKey() {
        return "target_id";
    }
}
