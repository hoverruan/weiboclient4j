package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum IsBatch {
    Yes(1), No(0);

    private int value;

    IsBatch(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
