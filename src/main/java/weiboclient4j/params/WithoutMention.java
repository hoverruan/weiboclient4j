package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum WithoutMention {
    Yes(1), No(0);

    private int value;

    WithoutMention(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
