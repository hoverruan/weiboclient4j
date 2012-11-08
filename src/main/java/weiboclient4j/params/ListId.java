package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class ListId extends StringParam {
    public ListId(String value) {
        super(value);
    }

    @Override
    protected String paramKey() {
        return "list_id";
    }
}
