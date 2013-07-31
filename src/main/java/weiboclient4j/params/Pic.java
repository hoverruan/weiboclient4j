package weiboclient4j.params;

public class Pic extends StringParam {
    public Pic(String value) {
        super(value);
    }

    protected String paramKey() {
        return "pic";
    }
}
