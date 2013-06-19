package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public enum LocSortType implements
        PlaceService.GetNearbyPhotosParam,
        PlaceService.GetNearbyUsersListParam,
        PlaceService.GetNearbyTimelineParam {

    Time(0), Dis(1), SocialRelationship(2);

    private int value;

    LocSortType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        params.add("sort", getValue());
    }
}
