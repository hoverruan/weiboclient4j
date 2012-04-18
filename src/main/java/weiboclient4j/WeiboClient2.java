package weiboclient4j;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import weiboclient4j.model.AccountUidResponse;
import weiboclient4j.model.Timeline;
import weiboclient4j.oauth2.DisplayType;
import weiboclient4j.oauth2.GrantType;
import weiboclient4j.oauth2.ResponseType;
import weiboclient4j.oauth2.SinaWeibo2AccessToken;
import weiboclient4j.oauth2.SinaWeibo2Api;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;

/**
 * @author Hover Ruan
 */
public class WeiboClient2 {
    public static final String BASE_URL = "https://api.weibo.com/2/";
    public static final String BASE_APP = "base_app";
    public static final String FEATURE = "feature";

    private String clientId;
    private String clientSecret;
    private SinaWeibo2AccessToken accessToken;

    public WeiboClient2(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getAuthorizationUrl(ResponseType responseType, DisplayType displayType, String state, String callback) {
        SinaWeibo2Api api = new SinaWeibo2Api(responseType, displayType);
        api.setState(state);

        OAuthService service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .callback(callback)
                .provider(api)
                .build();

        return service.getAuthorizationUrl(null);
    }

    public SinaWeibo2AccessToken getAccessToken(GrantType grantType, String code, String state, String callback) {
        SinaWeibo2Api api = new SinaWeibo2Api(grantType);
        api.setState(state);

        OAuthService service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .callback(callback)
                .provider(api)
                .build();

        accessToken = (SinaWeibo2AccessToken) service.getAccessToken(null, new Verifier(code));

        return accessToken;
    }

    public void setAccessToken(SinaWeibo2AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public long getAccountUid() throws WeiboClientException {
        OAuthRequest request = createGetRequest("account/get_uid");
        AccountUidResponse accountUidResponse = sendRequestAndGetResponseObject(request, AccountUidResponse.class);

        return accountUidResponse.getUid();
    }

    public Timeline getPublicTimeline() throws WeiboClientException {
        return getPublicTimeline(null);
    }

    public Timeline getPublicTimeline(Paging paging) throws WeiboClientException {
        return getPublicTimeline(paging, false);
    }

    public Timeline getPublicTimeline(Paging paging, boolean baseApp) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/public_timeline");
        Parameters params = Parameters.create();
        if (baseApp) {
            params.add(BASE_APP, baseApp);
        }
        return sendRequestAndGetResponseObject(request, paging, params, Timeline.class);
    }

    public Timeline getFriendsTimeline() throws WeiboClientException {
        return getFriendsTimeline(null);
    }

    public Timeline getFriendsTimeline(Paging paging) throws WeiboClientException {
        return getFriendsTimeline(paging, false, 0);
    }

    public Timeline getFriendsTimeline(Paging paging, boolean baseApp, int feature) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/friends_timeline");
        Parameters params = Parameters.create();
        if (baseApp) {
            params.add(BASE_APP, baseApp);
        }
        if (feature > 0) {
            params.add(FEATURE, feature);
        }
        return sendRequestAndGetResponseObject(request, paging, params, Timeline.class);
    }

    public Timeline getHomeTimeline() throws WeiboClientException {
        return getHomeTimeline(null);
    }

    public Timeline getHomeTimeline(Paging paging) throws WeiboClientException {
        return getHomeTimeline(paging, false, 0);
    }

    public Timeline getHomeTimeline(Paging paging, boolean baseApp, int feature) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/home_timeline");
        Parameters params = Parameters.create();
        if (baseApp) {
            params.add(BASE_APP, baseApp);
        }
        if (feature > 0) {
            params.add(FEATURE, feature);
        }
        return sendRequestAndGetResponseObject(request, paging, params, Timeline.class);
    }

    public <T> T sendRequestAndGetResponseObject(OAuthRequest request, Paging paging, Parameters params, Class<T> clazz)
            throws WeiboClientException {
        if (paging != null) {
            params.add(paging);
        }

        params.appendTo(request);

        return sendRequestAndGetResponseObject(request, clazz);
    }

    public <T> T sendRequestAndGetResponseObject(OAuthRequest request, Class<T> clazz) throws WeiboClientException {
        Response response = request.send();

        return parseJsonObject(response, clazz);
    }

    public OAuthRequest createGetRequest(String path) {
        OAuthRequest request = new OAuthRequest(Verb.GET, getFullPath(path));
        request.addQuerystringParameter("access_token", accessToken.getToken());

        return request;
    }

    public String getFullPath(String path) {
        return BASE_URL + path + ".json";
    }
}
