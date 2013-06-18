package weiboclient4j.params;

import weiboclient4j.LocationService;

public class CenterCoordinate extends StringParam implements
        LocationService.GetMapImageParam {

    public CenterCoordinate(String value) {
        super(value);
    }

    public CenterCoordinate(Coordinate coordinate) {
        this(coordinate.toString());
    }

    public CenterCoordinate(Longitude longitude, Latitude latitude) {
        this(new Coordinate(longitude, latitude));
    }

    protected String paramKey() {
        return "center_coordinate";
    }
}
