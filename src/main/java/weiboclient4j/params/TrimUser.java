package weiboclient4j.params;

import weiboclient4j.StatusService;

/**
 * @author Hover Ruan
 */
public enum TrimUser implements
        StatusService.GetFriendsTimelineParam,
        StatusService.GetHomeTimelineParam,
        StatusService.GetUserTimelineParam {

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

    public void addParameter(Parameters params) {
        if (this == Yes) {
            params.add("trim_user", getValue());
        }
    }
}
