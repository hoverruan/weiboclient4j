package weiboclient4j.params;

public class Ip extends StringParam {

    public Ip(String value) {
        super(value);
    }

    protected String paramKey() {
        return "ip";
    }
}
