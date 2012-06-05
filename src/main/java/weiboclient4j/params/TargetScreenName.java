package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class TargetScreenName extends StringParam {
    public static final TargetScreenName EMPTY = new TargetScreenName(null);

    public TargetScreenName(String value) {
        super(value);
    }
}
