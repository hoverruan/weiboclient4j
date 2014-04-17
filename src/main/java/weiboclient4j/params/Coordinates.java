package weiboclient4j.params;

import weiboclient4j.LocationService;
import static weiboclient4j.utils.StringUtils.join;

public class Coordinates extends StringParam implements
        LocationService.GetMapImageParam,
        LocationService.SearchByAreaParam {

    public Coordinates(String value) {
        super(value);
    }

    public Coordinates(Coordinate... coordinates) {
        this(join(coordinates, "|"));
    }

    protected String paramKey() {
        return "coordinates";
    }
}
