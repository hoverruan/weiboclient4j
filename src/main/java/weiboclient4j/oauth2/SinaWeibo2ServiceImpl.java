package weiboclient4j.oauth2;

import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuth20ServiceImpl;

/**
 * @author Hover Ruan
 */
public class SinaWeibo2ServiceImpl extends OAuth20ServiceImpl {
    public static final String GRANT_TYPE = "grant_type";

    private SinaWeibo2Api api;
    private OAuthConfig config;

    public SinaWeibo2ServiceImpl(SinaWeibo2Api api, OAuthConfig config) {
        super(api, config);

        this.api = api;
        this.config = config;
    }

    @Override
    public Token getAccessToken(Token requestToken, Verifier verifier) {
        OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenEndpoint());
        request.addBodyParameter(OAuthConstants.CLIENT_ID, config.getApiKey());
        request.addBodyParameter(OAuthConstants.CLIENT_SECRET, config.getApiSecret());
        request.addBodyParameter(GRANT_TYPE, api.getGrantType().getType());
        request.addBodyParameter(OAuthConstants.CODE, verifier.getValue());
        request.addBodyParameter(OAuthConstants.REDIRECT_URI, config.getCallback());
        Response response = request.send();
        return api.getAccessTokenExtractor().extract(response.getBody());
    }
}
