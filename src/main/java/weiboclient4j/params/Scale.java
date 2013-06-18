package weiboclient4j.params;

import weiboclient4j.LocationService;

public class Scale extends BooleanParam implements
        LocationService.GetMapImageParam {

    public Scale(boolean value) {
        super(value);
    }

    protected String paramKey() {
        return "scale";
    }
}
