package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Cid {
    private long value;

    public Cid(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
