package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class TargetUid {
    public static final TargetUid EMPTY = new TargetUid(0);

    private long value;

    public TargetUid(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public boolean isValid() {
        return value > 0;
    }
}
