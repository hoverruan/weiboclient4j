package weiboclient4j.params;

import weiboclient4j.LocationService;

public class Font extends StringParam implements
        LocationService.GetMapImageParam {

    public Font(String value) {
        super(value);
    }

    protected String paramKey() {
        return "font";
    }
}
