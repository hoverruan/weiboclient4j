package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public enum NearbySortType implements
        PlaceService.GetNearbyUsersParam,
        PlaceService.GetNearbyPoisParam {
    Weight(0);

    private int value;

    NearbySortType(int value) {
        this.value = value;
    }

    public void addParameter(Parameters params) {
        params.add("sort", value);
    }
}
