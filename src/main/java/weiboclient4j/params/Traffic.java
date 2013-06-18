package weiboclient4j.params;

import weiboclient4j.LocationService;

public class Traffic extends BooleanParam implements
        LocationService.GetMapImageParam {

    public Traffic(boolean value) {
        super(value);
    }

    protected String paramKey() {
        return "traffic";
    }
}
