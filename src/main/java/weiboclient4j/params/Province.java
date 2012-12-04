package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public class Province extends StringParam implements
        PlaceService.CreatePoiParam {

    public Province(String value) {
        super(value);
    }

    protected String paramKey() {
        return "province";
    }
}
