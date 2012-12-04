package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public class Address extends StringParam implements
        PlaceService.CreatePoiParam {

    public Address(String value) {
        super(value);
    }

    protected String paramKey() {
        return "address";
    }
}
