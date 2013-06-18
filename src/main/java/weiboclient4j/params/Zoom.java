package weiboclient4j.params;

import weiboclient4j.LocationService;

public class Zoom extends LongParam implements
        LocationService.GetMapImageParam {

    public Zoom(long value) {
        super(value);
    }

    protected String paramKey() {
        return "zoom";
    }
}
