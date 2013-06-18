package weiboclient4j.params;

import weiboclient4j.LocationService;
import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public class City extends StringParam implements
        PlaceService.SearchPoisParam,
        LocationService.GetMapImageParam {

    public City(String value) {
        super(value);
    }

    protected String paramKey() {
        return "city";
    }
}
