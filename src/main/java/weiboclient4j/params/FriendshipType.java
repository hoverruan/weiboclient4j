package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public enum FriendshipType implements
        PlaceService.GetFriendsTimelineParam {

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
