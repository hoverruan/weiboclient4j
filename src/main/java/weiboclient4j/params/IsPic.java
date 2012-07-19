package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum IsPic {
    All(0), Pic(1);

    private int value;

    IsPic(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
