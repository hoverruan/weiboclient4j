package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class StatusParam extends StringParam{
    public StatusParam(String value) {
        super(value);
    }

    protected String paramKey() {
        return "status";
    }
}
