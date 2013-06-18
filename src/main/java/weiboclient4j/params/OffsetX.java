package weiboclient4j.params;

import weiboclient4j.LocationService;

public class OffsetX extends StringParam implements
        LocationService.GetMapImageParam {

    public OffsetX(String value) {
        super(value);
    }

    protected String paramKey() {
        return "offset_x";
    }
}
