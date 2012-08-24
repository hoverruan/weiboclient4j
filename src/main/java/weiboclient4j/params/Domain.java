package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Domain extends StringParam {
    public Domain(String value) {
        super(value);
    }

    protected String paramKey() {
        return "domain";
    }
}
