package weiboclient4j.params;

import weiboclient4j.LocationService;

public class Polygons extends StringParam implements
        LocationService.GetMapImageParam {

    public Polygons(String value) {
        super(value);
    }

    protected String paramKey() {
        return "polygons";
    }
}
