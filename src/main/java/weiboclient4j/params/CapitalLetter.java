package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class CapitalLetter extends StringParam {
    public CapitalLetter(String value) {
        super(value);
    }

    protected String paramKey() {
        return "capital";
    }
}
