package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Query extends StringParam {
    public Query(String value) {
        super(value);
    }

    protected String paramKey() {
        return "q";
    }
}
