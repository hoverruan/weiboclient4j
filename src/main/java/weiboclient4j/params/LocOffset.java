package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public enum LocOffset implements
        PlaceService.GetNearbyUsersParam,
        PlaceService.GetNearbyPoisParam,
        PlaceService.GetNearbyPhotosParam {
    Original(0), Transformed(1);

    private int value;

    LocOffset(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        params.add("offset", getValue());
    }
}
