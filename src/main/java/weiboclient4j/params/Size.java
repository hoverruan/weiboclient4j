package weiboclient4j.params;

import weiboclient4j.LocationService;

public class Size extends StringParam implements
        LocationService.GetMapImageParam {

    public Size(String value) {
        super(value);
    }

    public Size(int width, int height) {
        this(String.format("%dx%d", width, height));
    }

    protected String paramKey() {
        return "size";
    }
}
