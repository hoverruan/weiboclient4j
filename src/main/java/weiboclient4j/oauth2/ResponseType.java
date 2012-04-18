package weiboclient4j.oauth2;

/**
 * @author Hover Ruan
 */
public enum ResponseType {
    Code("code"), Token("token");

    private String type;

    ResponseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
