package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum Language implements ParameterAction {
    ZH_CN("zh-cn"), ZH_TW("zh-tw"), ENGLISH("english");

    private String value;

    Language(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        params.add("language", getValue());
    }
}
