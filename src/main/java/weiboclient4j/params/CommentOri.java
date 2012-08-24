package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum CommentOri implements ParameterAction {
    Yes(1), No(0);

    private int value;

    CommentOri(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        if (this == Yes) {
            params.add("comment_ori", getValue());
        }
    }
}
