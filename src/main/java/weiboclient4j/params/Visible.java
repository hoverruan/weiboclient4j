package weiboclient4j.params;

import weiboclient4j.StatusService;

/**
 * @author Hover Ruan
 */
public enum Visible implements StatusService.UploadImageUrlParam {
    All(0), Myself(1), CloseFriend(2), List(3);

    private int value;

    Visible(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void addParameter(Parameters params) {
        if (this != All) {
            params.add("visible", getValue());
        }
    }
}
