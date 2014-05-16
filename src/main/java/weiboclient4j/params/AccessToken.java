package weiboclient4j.params;

import weiboclient4j.oauth2.SinaWeibo2AccessToken;

public class AccessToken extends StringParam {
    public AccessToken(String value) {
        super(value);
    }

    public AccessToken(SinaWeibo2AccessToken accessToken) {
        this(accessToken.getToken());
    }

    protected String paramKey() {
        return "access_token";
    }
}
