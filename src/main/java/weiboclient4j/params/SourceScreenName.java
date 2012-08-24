package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class SourceScreenName extends StringParam {
    public static final SourceScreenName EMPTY = new SourceScreenName(null);

    public SourceScreenName(String value) {
        super(value);
    }

    protected String paramKey() {
        return "source_screen_name";
    }
}
