package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public class Category extends StringParam implements
        PlaceService.GetNearbyPoisParam,
        PlaceService.SearchPoisParam {
    public Category(String value) {
        super(value);
    }

    protected String paramKey() {
        return "category";
    }
}
