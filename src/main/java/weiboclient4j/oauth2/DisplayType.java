package weiboclient4j.oauth2;

/**
 * @author Hover Ruan
 */
public enum DisplayType {

    Default("default"),

    Mobile("mobile"),

    Popup("popup"),

    Wap12("wap1.2"),

    Wap20("wap2.0"),

    Js("js"),

    Wap("wap"),

    Client("client"),

    AppOnWeibo("apponweibo");

    private String display;

    DisplayType(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
