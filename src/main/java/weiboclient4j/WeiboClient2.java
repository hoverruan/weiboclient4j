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
import weiboclient4j.params.BaseApp;
import weiboclient4j.params.Feature;
import weiboclient4j.params.Paging;
import weiboclient4j.params.Parameters;
import weiboclient4j.params.ScreenName;
import weiboclient4j.params.TrimUser;
import weiboclient4j.params.Uid;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;

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
        return getPublicTimeline(Paging.EMPTY);
    }

    public Timeline getPublicTimeline(Paging paging) throws WeiboClientException {
        return getPublicTimeline(paging, BaseApp.No);
    }

    public Timeline getPublicTimeline(Paging paging, BaseApp baseApp) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/public_timeline");
        Parameters params = Parameters.create();
        addBaseAppParam(params, baseApp);
        return sendRequestAndGetResponseObject(request, paging, params, Timeline.class);
    }

    public Timeline getFriendsTimeline() throws WeiboClientException {
        return getFriendsTimeline(Paging.EMPTY);
    }

    public Timeline getFriendsTimeline(Paging paging) throws WeiboClientException {
        return getFriendsTimeline(paging, BaseApp.No, Feature.All);
    }

    public Timeline getFriendsTimeline(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/friends_timeline");
        Parameters params = Parameters.create();
        addBaseAppParam(params, baseApp);
        addFeatureParam(params, feature);
        return sendRequestAndGetResponseObject(request, paging, params, Timeline.class);
    }

    public Timeline getHomeTimeline() throws WeiboClientException {
        return getHomeTimeline(Paging.EMPTY);
    }

    public Timeline getHomeTimeline(Paging paging) throws WeiboClientException {
        return getHomeTimeline(paging, BaseApp.No, Feature.All);
    }

    public Timeline getHomeTimeline(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/home_timeline");
        Parameters params = Parameters.create();
        addBaseAppParam(params, baseApp);
        addFeatureParam(params, feature);
        return sendRequestAndGetResponseObject(request, paging, params, Timeline.class);
    }

    public TimelineIds getFriendsTimelineIds() throws WeiboClientException {
        return getFriendsTimelineIds(Paging.EMPTY);
    }

    public TimelineIds getFriendsTimelineIds(Paging paging) throws WeiboClientException {
        return getFriendsTimelineIds(paging, BaseApp.No, Feature.All);
    }

    public TimelineIds getFriendsTimelineIds(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/friends_timeline/ids");
        Parameters params = Parameters.create();
        addBaseAppParam(params, baseApp);
        addFeatureParam(params, feature);
        return sendRequestAndGetResponseObject(request, paging, params, TimelineIds.class);
    }

    public Timeline getUserTimeline(ScreenName screenName) throws WeiboClientException {
        return getUserTimeline(screenName, Paging.EMPTY);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging) throws WeiboClientException {
        return getUserTimeline(Uid.EMPTY, screenName, paging);
    }

    public Timeline getUserTimeline(Uid uid) throws WeiboClientException {
        return getUserTimeline(uid, Paging.EMPTY);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging) throws WeiboClientException {
        return getUserTimeline(uid, ScreenName.EMPTY, paging);
    }

    public Timeline getUserTimeline(Uid uid, ScreenName screenName, Paging paging) throws WeiboClientException {
        return getUserTimeline(uid, screenName, paging, BaseApp.No, Feature.All, TrimUser.No);
    }

    public Timeline getUserTimeline() throws WeiboClientException {
        return getUserTimeline(Paging.EMPTY);
    }

    public Timeline getUserTimeline(TrimUser trimUser) throws WeiboClientException {
        return getUserTimeline(Paging.EMPTY, BaseApp.No, Feature.All, trimUser);
    }

    public Timeline getUserTimeline(Paging paging) throws WeiboClientException {
        return getUserTimeline(paging, BaseApp.No, Feature.All, TrimUser.No);
    }

    public Timeline getUserTimeline(Paging paging, TrimUser trimUser) throws WeiboClientException {
        return getUserTimeline(paging, BaseApp.No, Feature.All, trimUser);
    }

    public Timeline getUserTimeline(Paging paging, BaseApp baseApp, Feature feature, TrimUser trimUser) throws WeiboClientException {
        return getUserTimeline(Uid.EMPTY, ScreenName.EMPTY, paging, baseApp, feature, trimUser);
    }

    public Timeline getUserTimeline(Uid uid, ScreenName screenName, Paging paging, BaseApp baseApp, Feature feature, TrimUser trimUser)
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

    private void addFeatureParam(Parameters params, Feature feature) {
        if (feature.getValue() > 0) {
            params.add(FEATURE, feature.getValue());
        }
    }

    private void addTrimUserParam(Parameters params, TrimUser trimUser) {
        if (trimUser == TrimUser.Yes) {
            params.add(TRIM_USER, trimUser.getValue());
        }
    }

    private void addScreenNameParam(Parameters params, ScreenName screenName) {
        if (screenName != null && screenName.isValid()) {
            params.add(SCREEN_NAME, screenName.getValue());
        }
    }

    private void addUidParam(Parameters params, Uid uid) {
        if (uid != null && uid.isValid()) {
            params.add(UID, uid.getValue());
        }
    }

    private void addBaseAppParam(Parameters params, BaseApp baseApp) {
        if (baseApp == BaseApp.Yes) {
            params.add(BASE_APP, baseApp.getValue());
        }
    }
}
