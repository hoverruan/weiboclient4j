package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public class LocRange extends LongParam implements
        PlaceService.GetNearbyUsersParam,
        PlaceService.GetNearbyPoisParam,
        PlaceService.GetNearbyPhotosParam,
        PlaceService.GetNearbyUsersListParam,
        PlaceService.GetNearbyTimelineParam {

    public LocRange(long value) {
        super(value);
    }

    protected String paramKey() {
        return "range";
    }
}
