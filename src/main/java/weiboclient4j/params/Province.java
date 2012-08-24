package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Province extends StringParam {
    public Province(String value) {
        super(value);
    }

    protected String paramKey() {
        return "province";
    }
}
