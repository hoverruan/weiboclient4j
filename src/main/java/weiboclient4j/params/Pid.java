package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class Pid extends StringParam {
    public Pid(String value) {
        super(value);
    }

    protected String paramKey() {
        return "pid";
    }
}
