package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum TrimUser {
    Yes(1), No(0);

    private int value;

    TrimUser(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TrimUser fromValue(int value) {
        if (value == Yes.getValue()) {
            return Yes;
        } else if (value == No.getValue()) {
            return No;
        } else {
            return null;
        }
    }
}
