package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class CommentParam extends StringParam {
    public CommentParam(String value) {
        super(value);
    }

    protected String paramKey() {
        return "comment";
    }
}
