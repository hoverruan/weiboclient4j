package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Nickname extends StringParam {
    public Nickname(String value) {
        super(value);
    }

    protected String paramKey() {
        return "nickname";
    }
}
