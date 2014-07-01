package weiboclient4j.oauth2;

/**
 * @author Hover Ruan
 */
public enum ForceLogin {
    Yes(true), No(false);

    private boolean value;

    ForceLogin(boolean value) {
        this.value = value;
    }

    public String getValue() {
        return String.valueOf(value);
    }
}
