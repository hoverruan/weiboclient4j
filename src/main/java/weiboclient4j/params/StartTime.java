package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public class StartTime extends LongParam implements
        PlaceService.GetNearbyUsersParam,
        PlaceService.GetNearbyPhotosParam {
    public StartTime(long value) {
        super(value);
    }

    protected String paramKey() {
        return "starttime";
    }
}
