package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Title extends StringParam {
    public Title(String value) {
        super(value);
    }

    protected String paramKey() {
        return "title";
    }
}
