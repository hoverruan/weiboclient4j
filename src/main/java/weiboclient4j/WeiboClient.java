package weiboclient4j;

import org.codehaus.jackson.map.ObjectMapper;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.SinaWeiboApi;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import weiboclient4j.utils.SinaJsonNamingStrategy;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hover Ruan
 */
public class WeiboClient {
    private static final String BASE_URL = "http://api.t.sina.com.cn/";

    private OAuthService service;
    private Token accessToken;

    private long userId;
    private ObjectMapper mapper = new ObjectMapper();

    {
        mapper.setPropertyNamingStrategy(new SinaJsonNamingStrategy());
    }

    public WeiboClient(String apiKey, String apiSecret) {
        service = new ServiceBuilder()
                .provider(SinaWeiboApi.class)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .build();
    }

    public WeiboClient(Token accessToken) {
        setAccessToken(accessToken);
    }

    private static final Pattern USER_ID_PATTERN = Pattern.compile("user_id=(\\d+)");

    public void setAccessToken(Token accessToken) {
        this.accessToken = accessToken;

        Matcher matcher = USER_ID_PATTERN.matcher(accessToken.getRawResponse());
        if (matcher.find()) {
            userId = Long.parseLong(matcher.group(1));
        }
    }

    public Token getOAuthRequestToken() {
        return service.getRequestToken();
    }

    public String getAuthorizationURL(Token requestToken) {
        return service.getAuthorizationUrl(requestToken);
    }

    public Token getOAuthAccessToken(Token requestToken, Verifier verifier) {
        return service.getAccessToken(requestToken, verifier);
    }

    public long getUserId() {
        return userId;
    }

    public User showCurrentUser() throws WeiboClientException {
        return showUser(userId);
    }

    public User showUser(long userId) throws WeiboClientException {
        try {
            OAuthRequest request = new OAuthRequest(Verb.GET, BASE_URL + "users/show/" + userId + ".json");
            service.signRequest(accessToken, request);

            Response response = request.send();
            return mapper.readValue(response.getBody(), User.class);
        } catch (IOException e) {
            throw new WeiboClientException(e);
        }
    }

}
