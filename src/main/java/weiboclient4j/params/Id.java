package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Id {
    private long value;

    public Id(long value) {
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
