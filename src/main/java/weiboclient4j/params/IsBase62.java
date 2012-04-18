package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum IsBase62 {
    Yes(1), No(0);

    private int value;

    IsBase62(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
