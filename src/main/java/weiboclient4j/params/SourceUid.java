package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class SourceUid {
    public static final SourceUid EMPTY = new SourceUid(0);

    private long value;

    public SourceUid(long value) {
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
