package weiboclient4j.oauth2;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
    private String state = "";

    public SinaWeibo2Api() {
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
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }

    @Override
    public OAuthService createService(OAuthConfig config) {
        return new SinaWeibo2ServiceImpl(this, config);
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        try {
            return String.format(AUTHORIZE_URL,
                    config.getApiKey(),
                    URLEncoder.encode(config.getCallback(), "UTF-8"),
                    responseType.getType(),
                    state,
                    displayType.getDisplay());
        } catch (UnsupportedEncodingException e) {
            return String.format(AUTHORIZE_URL,
                    config.getApiKey(),
                    URLEncoder.encode(config.getCallback()),
                    responseType.getType(),
                    state,
                    displayType.getDisplay());
        }
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? "" : state;
    }
}
