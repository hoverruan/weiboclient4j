package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public class EndBirth extends LongParam implements
        PlaceService.GetNearbyUsersListParam {
    public EndBirth(long value) {
        super(value);
    }

    protected String paramKey() {
        return "endbirth";
    }
}
