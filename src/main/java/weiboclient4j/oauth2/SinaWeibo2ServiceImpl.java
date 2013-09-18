package weiboclient4j.oauth2;

import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuth20ServiceImpl;
import weiboclient4j.WeiboClientException;
import weiboclient4j.WeiboError;
import weiboclient4j.utils.JsonUtils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Hover Ruan
 */
public class SinaWeibo2ServiceImpl extends OAuth20ServiceImpl {
    public static final String GRANT_TYPE = "grant_type";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    private static final Logger log = Logger.getLogger(SinaWeibo2ServiceImpl.class.getName());

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

        if (api.getGrantType() == GrantType.AuthorizationCode) {
            request.addBodyParameter(OAuthConstants.CODE, verifier.getValue());
            request.addBodyParameter(OAuthConstants.REDIRECT_URI, config.getCallback());
        } else if (api.getGrantType() == GrantType.Password && requestToken != null) {
            request.addBodyParameter(USERNAME, requestToken.getToken());
            request.addBodyParameter(PASSWORD, requestToken.getSecret());
        }

        Response response = request.send();
        String responseBody = response.getBody();

        if (!response.isSuccessful()) {
            try {
                WeiboError error = JsonUtils.readValue(responseBody, WeiboError.class);
                if (error.isNotEmpty()) {
                    throw new OAuthException("Failed with weibo error", new WeiboClientException(error));
                }
            } catch (IOException e) {
                log.log(Level.WARNING, String.format("Failed to parse response: %d, %s", response.getCode(),
                        responseBody));
            }

            return null;
        }

        return api.getAccessTokenExtractor().extract(responseBody);
    }
}
