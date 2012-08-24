package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class ActionUrl extends StringParam {
    public static final ActionUrl EMPTY = new ActionUrl(null);

    public ActionUrl(String value) {
        super(value);
    }

    protected String paramKey() {
        return "action_url";
    }
}
