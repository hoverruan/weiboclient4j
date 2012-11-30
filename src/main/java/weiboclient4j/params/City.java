package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class City extends StringParam {
    public City(String value) {
        super(value);
    }

    protected String paramKey() {
        return "city";
    }
}
