package weiboclient4j;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import weiboclient4j.model.AccountUid;
import weiboclient4j.model.Mid;
import weiboclient4j.model.RepostTimeline;
import weiboclient4j.model.Status;
import weiboclient4j.model.Timeline;
import weiboclient4j.model.TimelineIds;
import weiboclient4j.oauth2.DisplayType;
import weiboclient4j.oauth2.GrantType;
import weiboclient4j.oauth2.ResponseType;
import weiboclient4j.oauth2.SinaWeibo2AccessToken;
import weiboclient4j.oauth2.SinaWeibo2Api;
import weiboclient4j.params.BaseApp;
import weiboclient4j.params.Feature;
import weiboclient4j.params.FilterByAuthor;
import weiboclient4j.params.FilterBySource;
import weiboclient4j.params.FilterByType;
import weiboclient4j.params.Id;
import weiboclient4j.params.IsBatch;
import weiboclient4j.params.MidType;
import weiboclient4j.params.Paging;
import weiboclient4j.params.Parameters;
import weiboclient4j.params.ScreenName;
import weiboclient4j.params.TrimUser;
import weiboclient4j.params.Uid;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;
import weiboclient4j.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    public static final String ID = "id";
    public static final String FILTER_BY_AUTHOR = "filter_by_author";
    public static final String FILTER_BY_SOURCE = "filter_by_source";
    public static final String FILTER_BY_TYPE = "filter_by_type";
    public static final String IS_BATCH = "is_batch";
    public static final String TYPE = "type";

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

    public TimelineIds getUserTimelineIds(ScreenName screenName) throws WeiboClientException {
        return getUserTimelineIds(screenName, Paging.EMPTY);
    }

    public TimelineIds getUserTimelineIds(ScreenName screenName, Paging paging) throws WeiboClientException {
        return getUserTimelineIds(Uid.EMPTY, screenName, paging);
    }

    public TimelineIds getUserTimelineIds(Uid uid) throws WeiboClientException {
        return getUserTimelineIds(uid, Paging.EMPTY);
    }

    public TimelineIds getUserTimelineIds(Uid uid, Paging paging) throws WeiboClientException {
        return getUserTimelineIds(uid, ScreenName.EMPTY, paging);
    }

    public TimelineIds getUserTimelineIds(Uid uid, ScreenName screenName, Paging paging) throws WeiboClientException {
        return getUserTimelineIds(uid, screenName, paging, BaseApp.No, Feature.All);
    }

    public TimelineIds getUserTimelineIds() throws WeiboClientException {
        return getUserTimelineIds(Paging.EMPTY);
    }

    public TimelineIds getUserTimelineIds(Paging paging) throws WeiboClientException {
        return getUserTimelineIds(paging, BaseApp.No, Feature.All);
    }

    public TimelineIds getUserTimelineIds(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        return getUserTimelineIds(Uid.EMPTY, ScreenName.EMPTY, paging, baseApp, feature);
    }

    public TimelineIds getUserTimelineIds(Uid uid, ScreenName screenName, Paging paging, BaseApp baseApp, Feature feature)
            throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/user_timeline/ids");
        Parameters params = Parameters.create();
        addUidParam(params, uid);
        addScreenNameParam(params, screenName);
        addBaseAppParam(params, baseApp);
        addFeatureParam(params, feature);
        return sendRequestAndGetResponseObject(request, paging, params, TimelineIds.class);
    }

    public RepostTimeline getRepostTimeline(Id id) throws WeiboClientException {
        return getRepostTimeline(id, Paging.EMPTY);
    }

    public RepostTimeline getRepostTimeline(Id id, Paging paging) throws WeiboClientException {
        return getRepostTimeline(id, paging, FilterByAuthor.All);
    }

    public RepostTimeline getRepostTimeline(Id id, Paging paging, FilterByAuthor filterByAuthor) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/repost_timeline");
        Parameters params = Parameters.create();
        addIdParam(params, id);
        addFilterByAuthorParam(params, filterByAuthor);
        return sendRequestAndGetResponseObject(request, paging, params, RepostTimeline.class);
    }

    public TimelineIds getRepostTimelineIds(Id id) throws WeiboClientException {
        return getRepostTimelineIds(id, Paging.EMPTY);
    }

    public TimelineIds getRepostTimelineIds(Id id, Paging paging) throws WeiboClientException {
        return getRepostTimelineIds(id, paging, FilterByAuthor.All);
    }

    public TimelineIds getRepostTimelineIds(Id id, Paging paging, FilterByAuthor filterByAuthor) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/repost_timeline/ids");
        Parameters params = Parameters.create();
        addIdParam(params, id);
        addFilterByAuthorParam(params, filterByAuthor);
        return sendRequestAndGetResponseObject(request, paging, params, TimelineIds.class);
    }

    public RepostTimeline getRepostByMe() throws WeiboClientException {
        return getRepostByMe(Paging.EMPTY);
    }

    public RepostTimeline getRepostByMe(Paging paging) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/repost_by_me");
        Parameters params = Parameters.create();
        return sendRequestAndGetResponseObject(request, paging, params, RepostTimeline.class);
    }

    public Timeline getMentions() throws WeiboClientException {
        return getMentions(Paging.EMPTY);
    }

    public Timeline getMentions(Paging paging) throws WeiboClientException {
        return getMentions(paging, FilterByAuthor.All, FilterBySource.All, FilterByType.All);
    }

    public Timeline getMentions(Paging paging, FilterByAuthor filterByAuthor, FilterBySource filterBySource, FilterByType filterByType)
            throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/mentions");
        Parameters params = Parameters.create();
        addFilterByAuthorParam(params, filterByAuthor);
        addFilterBySourceParam(params, filterBySource);
        addFilterByTypeParam(params, filterByType);
        return sendRequestAndGetResponseObject(request, paging, params, Timeline.class);
    }

    public TimelineIds getMentionsIds() throws WeiboClientException {
        return getMentionsIds(Paging.EMPTY);
    }

    public TimelineIds getMentionsIds(Paging paging) throws WeiboClientException {
        return getMentionsIds(paging, FilterByAuthor.All, FilterBySource.All, FilterByType.All);
    }

    public TimelineIds getMentionsIds(Paging paging, FilterByAuthor filterByAuthor, FilterBySource filterBySource, FilterByType filterByType)
            throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/mentions/ids");
        Parameters params = Parameters.create();
        addFilterByAuthorParam(params, filterByAuthor);
        addFilterBySourceParam(params, filterBySource);
        addFilterByTypeParam(params, filterByType);
        return sendRequestAndGetResponseObject(request, paging, params, TimelineIds.class);
    }

    public Timeline getBilateralTimeline() throws WeiboClientException {
        return getBilateralTimeline(Paging.EMPTY);
    }

    public Timeline getBilateralTimeline(Paging paging) throws WeiboClientException {
        return getBilateralTimeline(paging, BaseApp.No, Feature.All);
    }

    public Timeline getBilateralTimeline(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/bilateral_timeline");
        Parameters params = Parameters.create();
        addBaseAppParam(params, baseApp);
        addFeatureParam(params, feature);
        return sendRequestAndGetResponseObject(request, paging, params, Timeline.class);
    }

    public Status showStatus(Id id) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/show");
        Parameters params = Parameters.create();
        addIdParam(params, id);
        return sendRequestAndGetResponseObject(request, Paging.EMPTY, params, Status.class);
    }

    public String queryMid(Id id, MidType midType) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/querymid");
        Parameters params = Parameters.create();
        addIdParam(params, id);
        addMidTypeParam(params, midType);
        Mid mid = sendRequestAndGetResponseObject(request, Paging.EMPTY, params, Mid.class);
        return mid != null ? mid.getMid() : null;
    }

    public Map<Long, String> queryMidList(Collection<Id> idList, MidType midType) throws WeiboClientException {
        OAuthRequest request = createGetRequest("statuses/querymid");
        Parameters params = Parameters.create();
        addIdListParam(params, idList);
        addMidTypeParam(params, midType);
        addIsBatchParam(params, IsBatch.Yes);

        // [{"3436240135184587":"yfcLPlKKn"},{"3436255091659029":"yfd9X6XAx"}]
        ArrayNode arrayNode = sendRequestAndGetResponseObject(request, Paging.EMPTY, params, ArrayNode.class);
        Map<Long, String> map = new HashMap<Long, String>();
        for (int i = 0; i < arrayNode.size(); i++) {
            JsonNode node = arrayNode.get(i);
            Iterator<String> fieldNames = node.getFieldNames();
            while (fieldNames.hasNext()) {
                String idString = fieldNames.next();
                map.put(new Long(idString), node.get(idString).asText());
            }
        }

        return map;
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
        if (feature != null && feature != Feature.All) {
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

    private void addIdParam(Parameters params, Id id) {
        if (id != null && id.isValid()) {
            params.add(ID, id.getValue());
        }
    }

    private void addIdListParam(Parameters params, Collection<Id> idList) {
        if (idList != null && idList.size() > 0) {
            List<String> idStringList = new ArrayList<String>(idList.size());
            for (Id id : idList) {
                idStringList.add(String.valueOf(id.getValue()));
            }
            params.add(ID, StringUtils.join(idStringList, ","));
        }
    }

    private void addFilterByAuthorParam(Parameters params, FilterByAuthor filterByAuthor) {
        if (filterByAuthor != null && filterByAuthor != FilterByAuthor.All) {
            params.add(FILTER_BY_AUTHOR, filterByAuthor.getValue());
        }
    }

    private void addFilterBySourceParam(Parameters params, FilterBySource filterBySource) {
        if (filterBySource != null && filterBySource != FilterBySource.All) {
            params.add(FILTER_BY_SOURCE, filterBySource.getValue());
        }
    }

    private void addFilterByTypeParam(Parameters params, FilterByType filterByType) {
        if (filterByType != null && filterByType != FilterByType.All) {
            params.add(FILTER_BY_TYPE, filterByType.getValue());
        }
    }

    private void addMidTypeParam(Parameters params, MidType midType) {
        if (midType != null) {
            params.add(TYPE, midType.getValue());
        }
    }

    private void addIsBatchParam(Parameters params, IsBatch isBatch) {
        if (isBatch == IsBatch.Yes) {
            params.add(IS_BATCH, isBatch.getValue());
        }
    }
}
