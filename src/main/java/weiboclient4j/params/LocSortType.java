package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public enum LocSortType implements
        PlaceService.GetNearbyPhotosParam {
    Time(0), Dis(1);

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
