package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Category extends StringParam {
    public Category(String value) {
        super(value);
    }

    protected String paramKey() {
        return "category";
    }
}
