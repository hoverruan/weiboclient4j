package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum CommentOri {
    Yes(1), No(0);

    private int value;

    CommentOri(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
