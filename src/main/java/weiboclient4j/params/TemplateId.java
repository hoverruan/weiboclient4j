package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class TemplateId extends LongParam {
    public TemplateId(long value) {
        super(value);
    }

    protected String paramKey() {
        return "tpl_id";
    }
}
