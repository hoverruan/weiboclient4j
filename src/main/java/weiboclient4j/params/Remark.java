package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Remark extends StringParam {
    public Remark(String value) {
        super(value);
    }

    protected String paramKey() {
        return "remark";
    }
}
