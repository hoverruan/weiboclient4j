package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Suid {
    public static final Suid EMPTY = new Suid(0);

    private long value;

    public Suid(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public boolean isValid() {
        return value > 0;
    }
}
