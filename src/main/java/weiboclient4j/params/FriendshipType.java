package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum FriendshipType implements ParameterAction{
    Followed(0), Friends(1);

    private int value;

    FriendshipType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        if (this == Friends) {
            params.add("type", getValue());
        }
    }
}
