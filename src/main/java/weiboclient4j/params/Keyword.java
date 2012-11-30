package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Keyword extends StringParam {
    public Keyword(String value) {
        super(value);
    }

    protected String paramKey() {
        return "keyword";
    }
}
