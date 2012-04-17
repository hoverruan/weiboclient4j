package weiboclient4j.oauth2;

/**
 * @author Hover Ruan
 */
public enum GrantType {
    AuthorizationCode("authorization_code"), Password("password"), RefreshToken("refresh_token");

    private String type;

    GrantType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
