package weiboclient4j.params;

import weiboclient4j.PlaceService;
import weiboclient4j.StatusService;

/**
 * @author Hover Ruan
 */
public enum BaseApp implements
        PlaceService.GetPoiPhotosParam,
        PlaceService.GetPoiTipsParam,
        PlaceService.GetPoiUsersParam,
        PlaceService.GetPublishTimelineParam,
        PlaceService.GetUserTimelineParam,
        PlaceService.GetPoiTimelineParam,
        PlaceService.GetNearbyTimelineParam,
        PlaceService.GetUserCheckinsParam,
        PlaceService.GetUserPhotosParam,
        PlaceService.GetUserTipsParam,
        PlaceService.GetUserTodosParam,
        StatusService.GetFriendsTimelineParam,
        StatusService.GetPublicTimelineParam,
        StatusService.GetHomeTimelineParam,
        StatusService.GetFriendsTimelineIdsParam,
        StatusService.GetUserTimelineParam,
        StatusService.GetUserTimelineIdsParam,
        StatusService.GetBilateralTimelineParam {

    Yes(1), No(0);

    private int value;

    BaseApp(int value) {
        this.value = value;
    }

    public static BaseApp fromValue(int value) {
        if (value == Yes.getValue()) {
            return Yes;
        } else if (value == No.getValue()) {
            return No;
        }

        return null;
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        if (this == Yes) {
            params.add("base_app", getValue());
        }
    }
}
