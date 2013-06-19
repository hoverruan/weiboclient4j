package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public enum PoiSortType implements
        PlaceService.GetPoiPhotosParam,
        PlaceService.GetPoiTipsParam {

    Time(0), Hot(1);

    private int value;

    PoiSortType(int value) {
        this.value = value;
    }

    public void addParameter(Parameters params) {
        if (this != Time) {
            params.add("sort", value);
        }
    }
}
