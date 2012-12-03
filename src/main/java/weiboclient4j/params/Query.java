package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public class Query extends StringParam implements
        PlaceService.GetNearbyPoisParam {
    public Query(String value) {
        super(value);
    }

    protected String paramKey() {
        return "q";
    }
}
