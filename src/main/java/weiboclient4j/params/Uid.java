package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Uid {
    public static final Uid EMPTY = new Uid(0);

    private long value;

    public Uid(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public boolean isValid() {
        return value > 0;
    }
}
