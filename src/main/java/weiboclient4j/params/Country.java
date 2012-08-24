package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Country extends StringParam {
    public Country(String value) {
        super(value);
    }

    protected String paramKey() {
        return "country";
    }
}
