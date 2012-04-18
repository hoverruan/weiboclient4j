package weiboclient4j;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import weiboclient4j.model.AccountUid;
import weiboclient4j.model.Timeline;
import weiboclient4j.model.TimelineIds;
import weiboclient4j.oauth2.DisplayType;
import weiboclient4j.oauth2.GrantType;
import weiboclient4j.oauth2.ResponseType;
import weiboclient4j.oauth2.SinaWeibo2AccessToken;
import weiboclient4j.oauth2.SinaWeibo2Api;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;
import static weiboclient4j.utils.StringUtils.isNotBlank;

/**
 * @author Hover Ruan
 */
public class WeiboClient2 {
    public static final String API2_URL = "https://api.weibo.com/2/";

    public static final String BASE_APP = "base_app";
    public static final String FEATURE = "feature";
    public static final String UID = "uid";
    public static final String SCREEN_NAME = "screen_name";
    public static final String TRIM_USER = "trim_user";

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
        AccountUid accountUid = sendRequestAndGetResponseObject(request, AccountUid.class);

        return accountUid.getUid();
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
        addBaseAppParam(params, baseApp);
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
        addBaseAppParam(params, baseApp);
        addFeatureParam(params, feature);
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
        addBaseAppParam(params, baseApp);
        addFeatureParam(params, feature);
        return sendRequestAndGetResponseObject(request, paging, params, Timeline.class);
    }

    public TimelineIds getFriendsTimelineIds() throws WeiboClientException {
        return getFriendsTimelineIds(null);
    }

    public TimelineIds getFriendsTimelineIds(Paging paging) throws WeiboClientException {
        return getFriendsTimelineIds(paging, false, 0);
    }

    public TimelineIds getFriendsTimelineIds(Paging paging, boolean baseApp, int feature) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/friends_timeline/ids");
        Parameters params = Parameters.create();
        addBaseAppParam(params, baseApp);
        addFeatureParam(params, feature);
        return sendRequestAndGetResponseObject(request, paging, params, TimelineIds.class);
    }

    public Timeline getUserTimeline(String screenName) throws WeiboClientException {
        return getUserTimeline(screenName, null);
    }

    public Timeline getUserTimeline(String screenName, Paging paging) throws WeiboClientException {
        return getUserTimeline(0, screenName, paging);
    }

    public Timeline getUserTimeline(long uid) throws WeiboClientException {
        return getUserTimeline(uid, null);
    }

    public Timeline getUserTimeline(long uid, Paging paging) throws WeiboClientException {
        return getUserTimeline(uid, null, paging);
    }

    public Timeline getUserTimeline(long uid, String screenName, Paging paging) throws WeiboClientException {
        return getUserTimeline(uid, screenName, paging, false, 0, false);
    }

    public Timeline getUserTimeline() throws WeiboClientException {
        return getUserTimeline((Paging) null);
    }

    public Timeline getUserTimeline(boolean trimUser) throws WeiboClientException {
        return getUserTimeline(null, false, 0, trimUser);
    }

    public Timeline getUserTimeline(Paging paging) throws WeiboClientException {
        return getUserTimeline(paging, false, 0, false);
    }

    public Timeline getUserTimeline(Paging paging, boolean trimUser) throws WeiboClientException {
        return getUserTimeline(paging, false, 0, trimUser);
    }

    public Timeline getUserTimeline(Paging paging, boolean baseApp, int feature, boolean trimUser) throws WeiboClientException {
        return getUserTimeline(0, null, paging, baseApp, feature, trimUser);
    }

    public Timeline getUserTimeline(long uid, String screenName, Paging paging, boolean baseApp, int feature, boolean trimUser)
            throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/user_timeline");
        Parameters params = Parameters.create();
        addUidParam(params, uid);
        addScreenNameParam(params, screenName);
        addTrimUserParam(params, trimUser);
        addBaseAppParam(params, baseApp);
        addFeatureParam(params, feature);
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
        return API2_URL + path + ".json";
    }

    private void addFeatureParam(Parameters params, int feature) {
        if (feature > 0) {
            params.add(FEATURE, feature);
        }
    }

    private void addTrimUserParam(Parameters params, boolean trimUser) {
        if (trimUser) {
            params.add(TRIM_USER, trimUser);
        }
    }

    private void addScreenNameParam(Parameters params, String screenName) {
        if (isNotBlank(screenName)) {
            params.add(SCREEN_NAME, screenName);
        }
    }

    private void addUidParam(Parameters params, long uid) {
        if (uid > 0) {
            params.add(UID, uid);
        }
    }

    private void addBaseAppParam(Parameters params, boolean baseApp) {
        if (baseApp) {
            params.add(BASE_APP, baseApp);
        }
    }
}
