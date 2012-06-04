package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Tid {
    private long value;

    public Tid(long value) {
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
