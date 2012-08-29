package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class PoiId extends StringParam {
    public PoiId(String value) {
        super(value);
    }

    protected String paramKey() {
        return "poiid";
    }
}
