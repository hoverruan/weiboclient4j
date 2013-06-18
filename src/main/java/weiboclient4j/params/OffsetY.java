package weiboclient4j.params;

import weiboclient4j.LocationService;

public class OffsetY extends StringParam implements
        LocationService.GetMapImageParam {

    public OffsetY(String value) {
        super(value);
    }

    protected String paramKey() {
        return "offset_y";
    }
}
