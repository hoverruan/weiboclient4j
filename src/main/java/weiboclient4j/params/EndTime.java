package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public class EndTime extends LongParam implements
        PlaceService.GetNearbyUsersParam,
        PlaceService.GetNearbyPhotosParam,
        PlaceService.GetNearbyTimelineParam {

    public EndTime(long value) {
        super(value);
    }

    protected String paramKey() {
        return "endtime";
    }
}
