package weiboclient4j.params;

import weiboclient4j.LocationService;

public class Format extends StringParam implements
        LocationService.GetMapImageParam {

    public Format(String value) {
        super(value);
    }

    protected String paramKey() {
        return "format";
    }
}
