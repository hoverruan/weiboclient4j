package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Section extends LongParam {
    public Section(long value) {
        super(value);
    }

    protected String paramKey() {
        return "section";
    }
}
