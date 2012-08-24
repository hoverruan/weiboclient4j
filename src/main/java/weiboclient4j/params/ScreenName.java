package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class ScreenName extends StringParam {
    public static final ScreenName EMPTY = new ScreenName(null);

    public ScreenName(String value) {
        super(value);
    }

    protected String paramKey() {
        return "screen_name";
    }
}
