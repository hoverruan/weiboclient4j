package weiboclient4j.oauth2;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.model.OAuthConfig;

/**
 * @author Hover Ruan
 */
public class SinaWeibo2Api extends DefaultApi20 {
    private static final String ACCESS_TOKEN_URL =
            "https://api.weibo.com/oauth2/access_token";

    private static final String AUTHORIZE_URL =
            "https://api.weibo.com/oauth2/authorize?" +
                    "client_id=%s" +
                    "&redirect_uri=%s" +
                    "&response_type=%s" +
                    "&state=%s" +
                    "&display=%s";

    private ResponseType responseType = ResponseType.Code;
    private DisplayType displayType = DisplayType.Default;
    private GrantType grantType = GrantType.RefreshToken;

    public SinaWeibo2Api() {
    }

    public SinaWeibo2Api(ResponseType responseType) {
        this.responseType = responseType;
    }

    public SinaWeibo2Api(DisplayType displayType) {
        this.displayType = displayType;
    }

    public SinaWeibo2Api(GrantType grantType) {
        this.grantType = grantType;
    }

    public SinaWeibo2Api(ResponseType responseType, DisplayType displayType) {
        this.responseType = responseType;
        this.displayType = displayType;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return ACCESS_TOKEN_URL;
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        return String.format(AUTHORIZE_URL,
                config.getApiKey(),
                config.getCallback(),
                responseType.getType(),
                "", // TODO set "state" parameter
                displayType.getDisplay());
    }

    @Override
    public AccessTokenExtractor getAccessTokenExtractor() {
        return new SinaWeibo2AccessTokenExtractor();
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }

    public void setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
    }

    public GrantType getGrantType() {
        return grantType;
    }

    public void setGrantType(GrantType grantType) {
        this.grantType = grantType;
    }
}
