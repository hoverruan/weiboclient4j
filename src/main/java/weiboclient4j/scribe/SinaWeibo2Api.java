package weiboclient4j.scribe;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;

/**
 * @author Hover Ruan
 */
public class SinaWeibo2Api extends DefaultApi20 {

    private static final String ACCESS_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
    private static final String AUTHORIZE_URL = "https://api.weibo.com/oauth2/authorize?response_type=&client_id=%s&redirect_uri=%s";

    @Override
    public String getAccessTokenEndpoint() {
        return ACCESS_TOKEN_URL;
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        return String.format(AUTHORIZE_URL, config.getApiKey(), config.getCallback());
    }
}
