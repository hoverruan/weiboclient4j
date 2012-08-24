package weiboclient4j;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.type.TypeReference;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import weiboclient4j.model.AccountUid;
import weiboclient4j.model.Comment;
import weiboclient4j.model.CommentList;
import weiboclient4j.model.Count;
import weiboclient4j.model.Emotion;
import weiboclient4j.model.Favorites;
import weiboclient4j.model.FavoritesIds;
import weiboclient4j.model.FavoritesItem;
import weiboclient4j.model.FavoritesTags;
import weiboclient4j.model.Friendship;
import weiboclient4j.model.GlobalTrendList;
import weiboclient4j.model.IdResponse;
import weiboclient4j.model.MidResponse;
import weiboclient4j.model.NotificationResult;
import weiboclient4j.model.Privacy;
import weiboclient4j.model.RateLimitStatus;
import weiboclient4j.model.RepostTimeline;
import weiboclient4j.model.SearchSuggestionAppResult;
import weiboclient4j.model.SearchSuggestionAtUserResult;
import weiboclient4j.model.SearchSuggestionCompanyResult;
import weiboclient4j.model.SearchSuggestionSchoolResult;
import weiboclient4j.model.SearchSuggestionStatusResult;
import weiboclient4j.model.SearchSuggestionUserResult;
import weiboclient4j.model.Status;
import weiboclient4j.model.StatusIdList;
import weiboclient4j.model.StatusList;
import weiboclient4j.model.Tag;
import weiboclient4j.model.Timeline;
import weiboclient4j.model.TimelineIds;
import weiboclient4j.model.Trend;
import weiboclient4j.model.TrendStatus;
import weiboclient4j.model.UnreadCount;
import weiboclient4j.model.Url;
import weiboclient4j.model.UrlInfo;
import weiboclient4j.model.User;
import weiboclient4j.model.UserCount;
import weiboclient4j.model.UserIdList;
import weiboclient4j.model.UserList;
import weiboclient4j.model.UserTagList;
import weiboclient4j.model.VerifyNicknameResult;
import weiboclient4j.oauth2.DisplayType;
import weiboclient4j.oauth2.GrantType;
import weiboclient4j.oauth2.ResponseType;
import weiboclient4j.oauth2.SinaWeibo2AccessToken;
import weiboclient4j.oauth2.SinaWeibo2Api;
import weiboclient4j.params.ActionUrl;
import weiboclient4j.params.AddressCode;
import weiboclient4j.params.BaseApp;
import weiboclient4j.params.CapitalLetter;
import weiboclient4j.params.Cid;
import weiboclient4j.params.CommentOri;
import weiboclient4j.params.CommentParam;
import weiboclient4j.params.Content;
import weiboclient4j.params.CountType;
import weiboclient4j.params.Country;
import weiboclient4j.params.Domain;
import weiboclient4j.params.Feature;
import weiboclient4j.params.FilterByAuthor;
import weiboclient4j.params.FilterBySource;
import weiboclient4j.params.FilterByType;
import weiboclient4j.params.Id;
import weiboclient4j.params.InboxType;
import weiboclient4j.params.IsBase62;
import weiboclient4j.params.IsBatch;
import weiboclient4j.params.IsComment;
import weiboclient4j.params.IsPic;
import weiboclient4j.params.Language;
import weiboclient4j.params.Latitude;
import weiboclient4j.params.Longitude;
import weiboclient4j.params.Mid;
import weiboclient4j.params.MidType;
import weiboclient4j.params.Nickname;
import weiboclient4j.params.Num;
import weiboclient4j.params.Paging;
import weiboclient4j.params.ParameterAction;
import weiboclient4j.params.Parameters;
import weiboclient4j.params.Province;
import weiboclient4j.params.Query;
import weiboclient4j.params.Remark;
import weiboclient4j.params.SchoolType;
import weiboclient4j.params.ScreenName;
import weiboclient4j.params.Section;
import weiboclient4j.params.SourceScreenName;
import weiboclient4j.params.SourceUid;
import weiboclient4j.params.StatusParam;
import weiboclient4j.params.SuggestionRange;
import weiboclient4j.params.SuggestionStatusType;
import weiboclient4j.params.SuggestionType;
import weiboclient4j.params.SuggestionUserCategory;
import weiboclient4j.params.Suid;
import weiboclient4j.params.TagId;
import weiboclient4j.params.TagName;
import weiboclient4j.params.TargetScreenName;
import weiboclient4j.params.TargetUid;
import weiboclient4j.params.TemplateId;
import weiboclient4j.params.Tid;
import weiboclient4j.params.TrendId;
import weiboclient4j.params.TrendName;
import weiboclient4j.params.TrimUser;
import weiboclient4j.params.Uid;
import weiboclient4j.params.UrlLong;
import weiboclient4j.params.UrlShort;
import weiboclient4j.params.WithoutMention;
import weiboclient4j.utils.CollectionUtils;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;
import weiboclient4j.utils.StringUtils;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * API V2 client.
 *
 * @author Hover Ruan
 */
public class WeiboClient2 {
    public static final String API2_URL = "https://api.weibo.com/2/";
    public static final String ACCESS_TOKEN = "access_token";

    private String clientId;
    private String clientSecret;
    private SinaWeibo2AccessToken accessToken;

    private int connectTimeoutDuration = 30;
    private TimeUnit connectTimeoutUnit = TimeUnit.SECONDS;
    private int readTimeoutDuration = 30;
    private TimeUnit readTimeoutUnit = TimeUnit.SECONDS;

    /**
     * Create api client v2.
     *
     * @param clientId Client ID, or Api Key
     * @param clientSecret Client Secret, or Api Secret
     */
    public WeiboClient2(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public long getConnectTimeout() {
        return connectTimeoutUnit.toMillis(connectTimeoutDuration);
    }

    public void setConnectTimeout(int duration, TimeUnit unit) {
        this.connectTimeoutDuration = duration;
        this.connectTimeoutUnit = unit;
    }

    public long getReadTimeout() {
        return readTimeoutUnit.toMillis(readTimeoutDuration);
    }

    public void setReadTimeout(int duration, TimeUnit unit) {
        this.readTimeoutDuration = duration;
        this.readTimeoutUnit = unit;
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

    public SinaWeibo2AccessToken getAccessToken(GrantType grantType, String code, String callback) {
        SinaWeibo2Api api = new SinaWeibo2Api(grantType);

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
        AccountUid accountUid = doGet("account/get_uid", AccountUid.class);

        return accountUid.getUid();
    }

    public Timeline getPublicTimeline() throws WeiboClientException {
        return getPublicTimeline(Paging.EMPTY);
    }

    public Timeline getPublicTimeline(Paging paging) throws WeiboClientException {
        return getPublicTimeline(paging, BaseApp.No);
    }

    public Timeline getPublicTimeline(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return doGet("statuses/public_timeline",
                paging, withParams(baseApp), Timeline.class);
    }

    public Timeline getFriendsTimeline() throws WeiboClientException {
        return getFriendsTimeline(Paging.EMPTY);
    }

    public Timeline getFriendsTimeline(Paging paging) throws WeiboClientException {
        return getFriendsTimeline(paging, BaseApp.No, Feature.All);
    }

    public Timeline getFriendsTimeline(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return getFriendsTimeline(paging, baseApp, Feature.All);
    }

    public Timeline getFriendsTimeline(Paging paging, Feature feature) throws WeiboClientException {
        return getFriendsTimeline(paging, BaseApp.No, feature);
    }

    public Timeline getFriendsTimeline(Paging paging, TrimUser trimUser) throws WeiboClientException {
        return getFriendsTimeline(paging, BaseApp.No, trimUser);
    }

    public Timeline getFriendsTimeline(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        return getFriendsTimeline(paging, baseApp, feature, TrimUser.No);
    }

    public Timeline getFriendsTimeline(Paging paging, BaseApp baseApp, TrimUser trimUser) throws WeiboClientException {
        return getFriendsTimeline(paging, baseApp, Feature.All, trimUser);
    }

    public Timeline getFriendsTimeline(Paging paging, Feature feature, TrimUser trimUser) throws WeiboClientException {
        return getFriendsTimeline(paging, BaseApp.No, feature, trimUser);
    }

    public Timeline getFriendsTimeline(Paging paging, BaseApp baseApp, Feature feature, TrimUser trimUser) throws WeiboClientException {
        return doGet("statuses/friends_timeline",
                paging, withParams(baseApp, feature, trimUser), Timeline.class);
    }

    public Timeline getHomeTimeline() throws WeiboClientException {
        return getHomeTimeline(Paging.EMPTY);
    }

    public Timeline getHomeTimeline(Paging paging) throws WeiboClientException {
        return getHomeTimeline(paging, BaseApp.No, Feature.All);
    }

    public Timeline getHomeTimeline(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return getHomeTimeline(paging, baseApp, Feature.All);
    }

    public Timeline getHomeTimeline(Paging paging, Feature feature) throws WeiboClientException {
        return getHomeTimeline(paging, BaseApp.No, feature);
    }

    public Timeline getHomeTimeline(Paging paging, TrimUser trimUser) throws WeiboClientException {
        return getHomeTimeline(paging, BaseApp.No, trimUser);
    }

    public Timeline getHomeTimeline(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        return getHomeTimeline(paging, baseApp, feature, TrimUser.No);
    }

    public Timeline getHomeTimeline(Paging paging, BaseApp baseApp, TrimUser trimUser) throws WeiboClientException {
        return getHomeTimeline(paging, baseApp, Feature.All, trimUser);
    }

    public Timeline getHomeTimeline(Paging paging, Feature feature, TrimUser trimUser) throws WeiboClientException {
        return getHomeTimeline(paging, BaseApp.No, feature, trimUser);
    }

    public Timeline getHomeTimeline(Paging paging, BaseApp baseApp, Feature feature, TrimUser trimUser) throws WeiboClientException {
        return doGet("statuses/home_timeline",
                paging, withParams(baseApp, feature, trimUser), Timeline.class);
    }

    public TimelineIds getFriendsTimelineIds() throws WeiboClientException {
        return getFriendsTimelineIds(Paging.EMPTY);
    }

    public TimelineIds getFriendsTimelineIds(Paging paging) throws WeiboClientException {
        return getFriendsTimelineIds(paging, BaseApp.No, Feature.All);
    }

    public TimelineIds getFriendsTimelineIds(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        return doGet("statuses/friends_timeline/ids",
                paging, withParams(baseApp, feature), TimelineIds.class);
    }

    public Timeline getUserTimeline(ScreenName screenName) throws WeiboClientException {
        return getUserTimeline(screenName, Paging.EMPTY);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging) throws WeiboClientException {
        return getUserTimeline(Uid.EMPTY, screenName, paging);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging, BaseApp baseApp) throws WeiboClientException {
        return getUserTimeline(screenName, paging, baseApp, Feature.All);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging, Feature feature) throws WeiboClientException {
        return getUserTimeline(screenName, paging, BaseApp.No, feature);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging, TrimUser trimUser) throws WeiboClientException {
        return getUserTimeline(screenName, paging, BaseApp.No, trimUser);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging, Feature feature, TrimUser trimUser)
            throws WeiboClientException {
        return getUserTimeline(screenName, paging, BaseApp.No, feature, trimUser);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging, BaseApp baseApp, TrimUser trimUser)
            throws WeiboClientException {
        return getUserTimeline(screenName, paging, baseApp, Feature.All, trimUser);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging, BaseApp baseApp, Feature feature)
            throws WeiboClientException {
        return getUserTimeline(screenName, paging, baseApp, feature, TrimUser.No);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging, BaseApp baseApp, Feature feature, TrimUser trimUser)
            throws WeiboClientException {
        return getUserTimeline(Uid.EMPTY, screenName, paging, baseApp, feature, trimUser);
    }

    public Timeline getUserTimeline(Uid uid) throws WeiboClientException {
        return getUserTimeline(uid, Paging.EMPTY);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging) throws WeiboClientException {
        return getUserTimeline(uid, ScreenName.EMPTY, paging);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging, BaseApp baseApp) throws WeiboClientException {
        return getUserTimeline(uid, paging, baseApp, Feature.All);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging, Feature feature) throws WeiboClientException {
        return getUserTimeline(uid, paging, BaseApp.No, feature);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging, TrimUser trimUser) throws WeiboClientException {
        return getUserTimeline(uid, paging, BaseApp.No, trimUser);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging, BaseApp baseApp, Feature feature)
            throws WeiboClientException {
        return getUserTimeline(uid, paging, baseApp, feature, TrimUser.No);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging, BaseApp baseApp, TrimUser trimUser) throws WeiboClientException {
        return getUserTimeline(uid, paging, baseApp, Feature.All, trimUser);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging, Feature feature, TrimUser trimUser) throws WeiboClientException {
        return getUserTimeline(uid, paging, BaseApp.No, feature, trimUser);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging, BaseApp baseApp, Feature feature, TrimUser trimUser)
            throws WeiboClientException {
        return getUserTimeline(uid, ScreenName.EMPTY, paging, baseApp, feature, trimUser);
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

    public Timeline getUserTimeline(Paging paging, BaseApp baseApp, Feature feature, TrimUser trimUser)
            throws WeiboClientException {
        return getUserTimeline(Uid.EMPTY, ScreenName.EMPTY, paging, baseApp, feature, trimUser);
    }

    public Timeline getUserTimeline(Uid uid, ScreenName screenName, Paging paging, BaseApp baseApp,
                                    Feature feature, TrimUser trimUser) throws WeiboClientException {
        return doGet("statuses/user_timeline",
                paging, withParams(uid, screenName, trimUser, baseApp, feature), Timeline.class);
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
        return doGet("statuses/user_timeline/ids",
                paging, withParams(uid, screenName, baseApp, feature), TimelineIds.class);
    }

    public RepostTimeline getRepostTimeline(Id id) throws WeiboClientException {
        return getRepostTimeline(id, Paging.EMPTY);
    }

    public RepostTimeline getRepostTimeline(Id id, Paging paging) throws WeiboClientException {
        return getRepostTimeline(id, paging, FilterByAuthor.All);
    }

    public RepostTimeline getRepostTimeline(Id id, Paging paging, FilterByAuthor filterByAuthor) throws WeiboClientException {
        return doGet("statuses/repost_timeline",
                paging, withParams(id, filterByAuthor), RepostTimeline.class);
    }

    public TimelineIds getRepostTimelineIds(Id id) throws WeiboClientException {
        return getRepostTimelineIds(id, Paging.EMPTY);
    }

    public TimelineIds getRepostTimelineIds(Id id, Paging paging) throws WeiboClientException {
        return getRepostTimelineIds(id, paging, FilterByAuthor.All);
    }

    public TimelineIds getRepostTimelineIds(Id id, Paging paging, FilterByAuthor filterByAuthor) throws WeiboClientException {
        return doGet("statuses/repost_timeline/ids",
                paging, withParams(id, filterByAuthor), TimelineIds.class);
    }

    public RepostTimeline getRepostByMe() throws WeiboClientException {
        return getRepostByMe(Paging.EMPTY);
    }

    public RepostTimeline getRepostByMe(Paging paging) throws WeiboClientException {
        return doGet("statuses/repost_by_me", paging, RepostTimeline.class);
    }

    public Timeline getMentions() throws WeiboClientException {
        return getMentions(Paging.EMPTY);
    }

    public Timeline getMentions(Paging paging) throws WeiboClientException {
        return getMentions(paging, FilterByAuthor.All, FilterBySource.All, FilterByType.All);
    }

    public Timeline getMentions(Paging paging, FilterByAuthor filterByAuthor, FilterBySource filterBySource, FilterByType filterByType)
            throws WeiboClientException {
        return doGet("statuses/mentions",
                paging, withParams(filterByAuthor, filterBySource, filterByType), Timeline.class);
    }

    public TimelineIds getMentionsIds() throws WeiboClientException {
        return getMentionsIds(Paging.EMPTY);
    }

    public TimelineIds getMentionsIds(Paging paging) throws WeiboClientException {
        return getMentionsIds(paging, FilterByAuthor.All, FilterBySource.All, FilterByType.All);
    }

    public TimelineIds getMentionsIds(Paging paging, FilterByAuthor filterByAuthor, FilterBySource filterBySource,
                                      FilterByType filterByType) throws WeiboClientException {
        return doGet("statuses/mentions/ids",
                paging, withParams(filterByAuthor, filterBySource, filterByType), TimelineIds.class);
    }

    public Timeline getBilateralTimeline() throws WeiboClientException {
        return getBilateralTimeline(Paging.EMPTY);
    }

    public Timeline getBilateralTimeline(Paging paging) throws WeiboClientException {
        return getBilateralTimeline(paging, BaseApp.No, Feature.All);
    }

    public Timeline getBilateralTimeline(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        return doGet("statuses/bilateral_timeline",
                paging, withParams(baseApp, feature), Timeline.class);
    }

    public Status showStatus(Id id) throws WeiboClientException {
        return doGet("statuses/show", withParams(id), Status.class);
    }

    public String queryMid(Id id, MidType midType) throws WeiboClientException {
        MidResponse midResponse = doGet("statuses/querymid",
                withParams(id, midType), MidResponse.class);

        return midResponse != null ? midResponse.getMid() : null;
    }

    public Map<Long, String> queryMidList(Collection<Id> idList, MidType midType) throws WeiboClientException {
        // [{"3436240135184587":"yfcLPlKKn"},{"3436255091659029":"yfd9X6XAx"}]
        ArrayNode arrayNode = doGet("statuses/querymid",
                withParams(Id.idParam(idList), midType, IsBase62.Yes), ArrayNode.class);

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

    public long queryId(Mid mid, MidType type, IsBase62 isBase62) throws WeiboClientException {
        return queryId(mid, type, null, isBase62);
    }

    public long queryId(Mid mid, MidType type, InboxType inboxType, IsBase62 isBase62) throws WeiboClientException {
        IdResponse idResponse = doGet("statuses/queryid",
                withParams(mid, type, inboxType, isBase62), IdResponse.class);

        return idResponse.getId();
    }

    public Map<String, Long> queryIdList(Collection<Mid> midList, MidType type, IsBase62 isBase62) throws WeiboClientException {
        return queryIdList(midList, type, null, isBase62);
    }

    public Map<String, Long> queryIdList(Collection<Mid> midList, MidType type, InboxType inboxType, IsBase62 isBase62)
            throws WeiboClientException {
        // [{"yfcLPlKKn":"3436240135184587"},{"yfd9X6XAx":"3436255091659029"}]
        ArrayNode arrayNode = doGet("statuses/queryid",
                withParams(Mid.midParam(midList), type, inboxType, isBase62, IsBatch.Yes), ArrayNode.class);

        Map<String, Long> map = new HashMap<String, Long>();
        for (int i = 0; i < arrayNode.size(); i++) {
            JsonNode node = arrayNode.get(i);
            Iterator<String> fieldNames = node.getFieldNames();
            while (fieldNames.hasNext()) {
                String mid = fieldNames.next();
                map.put(mid, node.get(mid).asLong());
            }
        }

        return map;
    }

    public List<Status> getHotRepostDaily() throws WeiboClientException {
        return getHotRepostDaily(Paging.EMPTY);
    }

    public List<Status> getHotRepostDaily(Paging paging) throws WeiboClientException {
        return getHotRepostDaily(paging, BaseApp.No);
    }

    public List<Status> getHotRepostDaily(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return doGet("statuses/hot/repost_daily",
                paging, withParams(baseApp), Status.TYPE_STATUS_LIST);
    }

    public List<Status> getHotRepostWeekly() throws WeiboClientException {
        return getHotRepostWeekly(Paging.EMPTY);
    }

    public List<Status> getHotRepostWeekly(Paging paging) throws WeiboClientException {
        return getHotRepostWeekly(paging, BaseApp.No);
    }

    public List<Status> getHotRepostWeekly(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return doGet("statuses/hot/repost_weekly",
                paging, withParams(baseApp), Status.TYPE_STATUS_LIST);
    }

    public List<Status> getHotCommentsDaily() throws WeiboClientException {
        return getHotCommentsDaily(Paging.EMPTY);
    }

    public List<Status> getHotCommentsDaily(Paging paging) throws WeiboClientException {
        return getHotCommentsDaily(paging, BaseApp.No);
    }

    public List<Status> getHotCommentsDaily(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return doGet("statuses/hot/comments_daily",
                paging, withParams(baseApp), Status.TYPE_STATUS_LIST);
    }

    public List<Status> getHotCommentsWeekly() throws WeiboClientException {
        return getHotCommentsWeekly(Paging.EMPTY);
    }

    public List<Status> getHotCommentsWeekly(Paging paging) throws WeiboClientException {
        return getHotCommentsWeekly(paging, BaseApp.No);
    }

    public List<Status> getHotCommentsWeekly(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return doGet("statuses/hot/comments_weekly",
                paging, withParams(baseApp), Status.TYPE_STATUS_LIST);
    }

    public List<Count> getStatusesCounts(Collection<Id> ids) throws WeiboClientException {
        return doGet("statuses/count",
                withParams(Id.idsParam(ids)), Count.TYPE_COUNT_LIST);
    }

    public Status repostStatus(Id id, String status) throws WeiboClientException {
        return repostStatus(id, status, IsComment.No);
    }

    public Status repostStatus(Id id, String status, IsComment isComment) throws WeiboClientException {
        return repostStatus(id, new StatusParam(status), isComment);
    }

    public Status repostStatus(Id id, StatusParam status, IsComment isComment) throws WeiboClientException {
        return doPost("statuses/repost", withParams(id, status, isComment), Status.class);
    }

    public Status updateStatus(String status) throws WeiboClientException {
        return updateStatus(status, null, null);
    }

    public Status updateStatus(String status, Latitude latitude, Longitude longitude) throws WeiboClientException {
        return updateStatus(new StatusParam(status), latitude, longitude);
    }

    public Status updateStatus(StatusParam status, Latitude latitude, Longitude longitude) throws WeiboClientException {
        return doPost("statuses/update", withParams(status, latitude, longitude), Status.class);
    }

    public Status destroyStatus(Id id) throws WeiboClientException {
        return doPost("statuses/destroy", withParams(id), Status.class);
    }

    public Status uploadImageUrl(String status, URL url) throws WeiboClientException {
        return uploadImageUrl(status, url, null, null);
    }

    public Status uploadImageUrl(String status, URL url, Latitude latitude, Longitude longitude) throws WeiboClientException {
        return uploadImageUrl(new StatusParam(status), url, latitude, longitude);
    }

    public Status uploadImageUrl(StatusParam status, URL url, Latitude latitude, Longitude longitude) throws WeiboClientException {
        return doPost("statuses/upload_url_text",
                withParams(status, urlParam(url), latitude, longitude), Status.class);
    }

//    TODO implements update binary image
//    public Status uploadImageBinary() {
//
//    }

    public List<Emotion> getEmotions() throws WeiboClientException {
        return doGet("emotions", Emotion.TYPE_EMOTION_LIST);
    }

    public CommentList getComments(Id id) throws WeiboClientException {
        return getComments(id, Paging.EMPTY);
    }

    public CommentList getComments(Id id, Paging paging) throws WeiboClientException {
        return getComments(id, paging, FilterByAuthor.All);
    }

    public CommentList getComments(Id id, Paging paging, FilterByAuthor filterByAuthor) throws WeiboClientException {
        return doGet("comments/show",
                paging, withParams(id, filterByAuthor), CommentList.class);
    }

    public CommentList getCommentsByMe() throws WeiboClientException {
        return getCommentsByMe(Paging.EMPTY);
    }

    public CommentList getCommentsByMe(Paging paging) throws WeiboClientException {
        return getCommentsByMe(paging, FilterByAuthor.All);
    }

    public CommentList getCommentsByMe(Paging paging, FilterByAuthor filterByAuthor) throws WeiboClientException {
        return doGet("comments/by_me",
                paging, withParams(filterByAuthor), CommentList.class);
    }

    public CommentList getCommentsToMe() throws WeiboClientException {
        return getCommentsToMe(Paging.EMPTY);
    }

    public CommentList getCommentsToMe(Paging paging) throws WeiboClientException {
        return getCommentsToMe(paging, FilterByAuthor.All, FilterBySource.All);
    }

    public CommentList getCommentsToMe(Paging paging, FilterByAuthor filterByAuthor, FilterBySource filterBySource)
            throws WeiboClientException {
        return doGet("comments/to_me",
                paging, withParams(filterByAuthor, filterBySource), CommentList.class);
    }

    public CommentList getCommentsTimeline() throws WeiboClientException {
        return getCommentsTimeline(Paging.EMPTY);
    }

    public CommentList getCommentsTimeline(Paging paging) throws WeiboClientException {
        return doGet("comments/timeline", paging, CommentList.class);
    }

    public CommentList getMentionsComments() throws WeiboClientException {
        return getMentionsComments(Paging.EMPTY);
    }

    public CommentList getMentionsComments(Paging paging) throws WeiboClientException {
        return getMentionsComments(paging, FilterByAuthor.All, FilterBySource.All);
    }

    public CommentList getMentionsComments(Paging paging, FilterByAuthor filterByAuthor, FilterBySource filterBySource)
            throws WeiboClientException {
        return doGet("comments/mentions",
                paging, withParams(filterByAuthor, filterBySource), CommentList.class);
    }

    public List<Comment> getCommentsBatch(Collection<Cid> cids) throws WeiboClientException {
        return doGet("comments/show_batch",
                withParams(Cid.cidsParam(cids)), Comment.TYPE_COMMENT_LIST);
    }

    public Comment createComment(Id id, String comment) throws WeiboClientException {
        return createComment(id, comment, CommentOri.No);
    }

    public Comment createComment(Id id, CommentParam comment, CommentOri commentOri) throws WeiboClientException {
        return doPost("comments/create",
                withParams(id, comment, commentOri), Comment.class);
    }

    public Comment createComment(Id id, String comment, CommentOri commentOri) throws WeiboClientException {
        return createComment(id, new CommentParam(comment), commentOri);
    }

    public Comment destroyComment(Cid cid) throws WeiboClientException {
        return doPost("comments/destroy", withParams(cid), Comment.class);
    }

    public List<Comment> destroyCommentBatch(Collection<Cid> cids) throws WeiboClientException {
        List<Id> ids = new ArrayList<Id>(cids.size());
        for (Cid cid : cids) {
            ids.add(new Id(cid.getValue()));
        }

        return doPost("comments/destroy_batch",
                withParams(Id.idsParam(ids)), Comment.TYPE_COMMENT_LIST);
    }

    public Comment replyComment(Id id, Cid cid, String comment) throws WeiboClientException {
        return replyComment(id, cid, comment, WithoutMention.No, CommentOri.No);
    }

    public Comment replyComment(Id id, Cid cid, String comment, WithoutMention withoutMention, CommentOri commentOri)
            throws WeiboClientException {
        return replyComment(id, cid, new CommentParam(comment), withoutMention, commentOri);
    }

    public Comment replyComment(Id id, Cid cid, CommentParam comment, WithoutMention withoutMention, CommentOri commentOri)
            throws WeiboClientException {
        return doPost("comments/reply",
                withParams(id, cid, comment, withoutMention, commentOri), Comment.class);
    }

    public User showUser(ScreenName screenName) throws WeiboClientException {
        return showUser(Uid.EMPTY, screenName);
    }

    public User showUser(Uid uid) throws WeiboClientException {
        return showUser(uid, ScreenName.EMPTY);
    }

    private User showUser(Uid uid, ScreenName screenName) throws WeiboClientException {
        return doGet("users/show", withParams(uid, screenName), User.class);
    }

    public User showUserByDomain(Domain domain) throws WeiboClientException {
        return doGet("users/domain_show", withParams(domain), User.class);
    }

    public User showUserByDomain(String domain) throws WeiboClientException {
        return showUserByDomain(new Domain(domain));
    }

    public List<UserCount> getUsersCounts(Collection<Uid> uids) throws WeiboClientException {
        return doGet("users/counts", withParams(Uid.uidsParam(uids)), UserCount.LIST_TYPE);
    }

    public UserList getFriends(Uid uid) throws WeiboClientException {
        return getFriends(uid, Paging.EMPTY);
    }

    public UserList getFriends(Uid uid, Paging paging) throws WeiboClientException {
        return getFriends(uid, ScreenName.EMPTY, paging);
    }

    public UserList getFriends(ScreenName screenName) throws WeiboClientException {
        return getFriends(screenName, Paging.EMPTY);
    }

    public UserList getFriends(ScreenName screenName, Paging paging) throws WeiboClientException {
        return getFriends(Uid.EMPTY, screenName, paging);
    }

    private UserList getFriends(Uid uid, ScreenName screenName, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends",
                paging, withParams(uid, screenName), UserList.class);
    }

    public UserIdList getFriendsIds(Uid uid) throws WeiboClientException {
        return getFriendsIds(uid, Paging.EMPTY);
    }

    public UserIdList getFriendsIds(Uid uid, Paging paging) throws WeiboClientException {
        return getFriendsIds(uid, ScreenName.EMPTY, paging);
    }

    public UserIdList getFriendsIds(ScreenName screenName) throws WeiboClientException {
        return getFriendsIds(screenName, Paging.EMPTY);
    }

    public UserIdList getFriendsIds(ScreenName screenName, Paging paging) throws WeiboClientException {
        return getFriendsIds(Uid.EMPTY, screenName, paging);
    }

    private UserIdList getFriendsIds(Uid uid, ScreenName screenName, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends/ids",
                paging, withParams(uid, screenName), UserIdList.class);
    }

    public UserList getFriendsInCommon(Uid uid) throws WeiboClientException {
        return getFriendsInCommon(uid, Suid.EMPTY);
    }

    public UserList getFriendsInCommon(Uid uid, Paging paging) throws WeiboClientException {
        return getFriendsInCommon(uid, Suid.EMPTY, paging);
    }

    public UserList getFriendsInCommon(Uid uid, Suid suid) throws WeiboClientException {
        return getFriendsInCommon(uid, suid, Paging.EMPTY);
    }

    public UserList getFriendsInCommon(Uid uid, Suid suid, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends/in_common",
                paging, withParams(uid, suid), UserList.class);
    }

    public UserList getFriendsBilateral(Uid uid) throws WeiboClientException {
        return getFriendsBilateral(uid, Paging.EMPTY);
    }

    public UserList getFriendsBilateral(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends/bilateral",
                paging, withParams(uid), UserList.class);
    }

    public UserIdList getFriendsBilateralIds(Uid uid) throws WeiboClientException {
        return getFriendsBilateralIds(uid, Paging.EMPTY);
    }

    public UserIdList getFriendsBilateralIds(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends/bilateral/ids",
                paging, withParams(uid), UserIdList.class);
    }

    public UserList getFollowers(ScreenName screenName) throws WeiboClientException {
        return getFollowers(screenName, Paging.EMPTY);
    }

    public UserList getFollowers(ScreenName screenName, Paging paging) throws WeiboClientException {
        return getFollowers(Uid.EMPTY, screenName, paging);
    }

    public UserList getFollowers(Uid uid) throws WeiboClientException {
        return getFollowers(uid, Paging.EMPTY);
    }

    public UserList getFollowers(Uid uid, Paging paging) throws WeiboClientException {
        return getFollowers(uid, ScreenName.EMPTY, paging);
    }

    public UserList getFollowers(Uid uid, ScreenName screenName, Paging paging) throws WeiboClientException {
        return doGet("friendships/followers",
                paging, withParams(uid, screenName), UserList.class);
    }

    public UserIdList getFollowersIds(ScreenName screenName) throws WeiboClientException {
        return getFollowersIds(screenName, Paging.EMPTY);
    }

    public UserIdList getFollowersIds(ScreenName screenName, Paging paging) throws WeiboClientException {
        return getFollowersIds(Uid.EMPTY, screenName, paging);
    }

    public UserIdList getFollowersIds(Uid uid) throws WeiboClientException {
        return getFollowersIds(uid, Paging.EMPTY);
    }

    public UserIdList getFollowersIds(Uid uid, Paging paging) throws WeiboClientException {
        return getFollowersIds(uid, ScreenName.EMPTY, paging);
    }

    public UserIdList getFollowersIds(Uid uid, ScreenName screenName, Paging paging) throws WeiboClientException {
        return doGet("friendships/followers/ids",
                paging, withParams(uid, screenName), UserIdList.class);
    }

    public UserList getActiveFollowers(Uid uid) throws WeiboClientException {
        return getActiveFollowers(uid, Paging.EMPTY);
    }

    public UserList getActiveFollowers(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("friendships/followers/active",
                paging, withParams(uid), UserList.class);
    }

    public UserList getChainFollowers(Uid uid) throws WeiboClientException {
        return getChainFollowers(uid, Paging.EMPTY);
    }

    public UserList getChainFollowers(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends_chain/followers",
                paging, withParams(uid), UserList.class);
    }

    public Friendship showFriendship(SourceUid sourceUid, TargetUid targetUid) throws WeiboClientException {
        return showFriendship(sourceUid, SourceScreenName.EMPTY, targetUid, TargetScreenName.EMPTY);
    }

    public Friendship showFriendship(SourceScreenName sourceScreenName, TargetScreenName targetScreenName)
            throws WeiboClientException {
        return showFriendship(SourceUid.EMPTY, sourceScreenName, TargetUid.EMPTY, targetScreenName);
    }

    public Friendship showFriendship(SourceUid sourceUid, SourceScreenName sourceScreenName,
                                     TargetUid targetUid, TargetScreenName targetScreenName) throws WeiboClientException {
        return doGet("friendships/show",
                withParams(sourceUid, sourceScreenName, targetUid, targetScreenName), Friendship.class);
    }

    public User createFriendship(Uid uid) throws WeiboClientException {
        return createFriendship(uid, ScreenName.EMPTY);
    }

    public User createFriendship(ScreenName screenName) throws WeiboClientException {
        return createFriendship(Uid.EMPTY, screenName);
    }

    public User createFriendship(Uid uid, ScreenName screenName) throws WeiboClientException {
        return doPost("friendships/create",
                withParams(uid, screenName), User.class);
    }

    public User destroyFriendship(Uid uid) throws WeiboClientException {
        return destroyFriendship(uid, ScreenName.EMPTY);
    }

    public User destroyFriendship(ScreenName screenName) throws WeiboClientException {
        return destroyFriendship(Uid.EMPTY, screenName);
    }

    public User destroyFriendship(Uid uid, ScreenName screenName) throws WeiboClientException {
        return doPost("friendships/destroy",
                withParams(uid, screenName), User.class);
    }

    public User updateRemark(Uid uid, Remark remark) throws WeiboClientException {
        return doPost("friendships/remark/update",
                withParams(uid, remark), User.class);
    }

    public Privacy getPrivacy() throws WeiboClientException {
        return doGet("account/get_privacy", Privacy.class);
    }

    public RateLimitStatus getRateLimitStatus() throws WeiboClientException {
        RawRateLimitStatus rawRateLimitStatus = doGet("account/rate_limit_status", RawRateLimitStatus.class);

        try {
            return rawRateLimitStatus.asRateLimitStatus();
        } catch (ParseException e) {
            throw new WeiboClientException("Invalid date format (reset_time): " + rawRateLimitStatus.getResetTime());
        }
    }

    public User endSession() throws WeiboClientException {
        return doGet("account/end_session", User.class);
    }

    public Favorites getFavorites() throws WeiboClientException {
        return getFavorites(Paging.EMPTY);
    }

    public Favorites getFavorites(Paging paging) throws WeiboClientException {
        return doGet("favorites", paging, Favorites.class);
    }

    public FavoritesIds getFavoritesIds() throws WeiboClientException {
        return getFavoritesIds(Paging.EMPTY);
    }

    public FavoritesIds getFavoritesIds(Paging paging) throws WeiboClientException {
        return doGet("favorites/ids", paging, FavoritesIds.class);
    }

    public FavoritesItem showFavorites(Id id) throws WeiboClientException {
        return doGet("favorites/show", withParams(id), FavoritesItem.class);
    }

    public Favorites getFavoritesByTags(Tid tid) throws WeiboClientException {
        return getFavoritesByTags(tid, Paging.EMPTY);
    }

    public Favorites getFavoritesByTags(Tid tid, Paging paging) throws WeiboClientException {
        return doGet("favorites/by_tags", paging, withParams(tid), Favorites.class);
    }

    public FavoritesTags getFavoritesTags() throws WeiboClientException {
        return getFavoritesTags(Paging.EMPTY);
    }

    public FavoritesTags getFavoritesTags(Paging paging) throws WeiboClientException {
        return doGet("favorites/tags", paging, FavoritesTags.class);
    }

    public FavoritesIds getFavoritesIdsByTags(Tid tid) throws WeiboClientException {
        return getFavoritesIdsByTags(tid, Paging.EMPTY);
    }

    public FavoritesIds getFavoritesIdsByTags(Tid tid, Paging paging) throws WeiboClientException {
        return doGet("favorites/by_tags/ids", paging, withParams(tid), FavoritesIds.class);
    }

    public FavoritesItem createFavorites(Id id) throws WeiboClientException {
        return doPost("favorites/create", withParams(id), FavoritesItem.class);
    }

    public FavoritesItem destroyFavorites(Id id) throws WeiboClientException {
        return doPost("favorites/destroy", withParams(id), FavoritesItem.class);
    }

    public boolean destroyFavoritesBatch(Collection<Id> ids) throws WeiboClientException {
        ResultResponse response = doPost("favorites/destroy_batch",
                withParams(Id.idsParam(ids)), ResultResponse.class);

        return response.isResult();
    }

    public FavoritesItem updateFavoritesTags(Id id) throws WeiboClientException {
        return updateFavoritesTags(id, null);
    }

    public FavoritesItem updateFavoritesTags(Id id, Collection<TagName> tags) throws WeiboClientException {
        return doPost("favorites/tags/update",
                withParams(id, TagName.tagsParam(tags)), FavoritesItem.class);
    }

    public Tag updateFavoritesTagsBatch(Tid tid, TagName tagName) throws WeiboClientException {
        return doPost("favorites/tags/update_batch",
                withParams(tid, tagName), Tag.class);
    }

    public boolean destroyFavoritesTagsBatch(Tid tid) throws WeiboClientException {
        ResultResponse response = doPost("favorites/tags/destroy_batch",
                withParams(tid), ResultResponse.class);
        return response.isResult();
    }

    public Url shortenUrl(UrlLong urlLong) throws WeiboClientException {
        List<Url> result = shortenUrl(CollectionUtils.newArrayList(urlLong));

        return result != null && result.size() > 0 ? result.get(0) : null;
    }

    public List<Url> shortenUrl(Collection<UrlLong> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/shorten",
                withParams(UrlLong.urlLongParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public Url expandUrl(UrlShort urlShort) throws WeiboClientException {
        List<Url> result = expandUrl(CollectionUtils.newArrayList(urlShort));

        return result != null && result.size() > 0 ? result.get(0) : null;
    }

    public List<Url> expandUrl(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/expand",
                withParams(UrlShort.urlShortParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public List<Url> getShortUrlClicks(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/clicks",
                withParams(UrlShort.urlShortParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public Url getShortUrlReferers(UrlShort urlShort) throws WeiboClientException {
        return doGet("short_url/referers", withParams(urlShort), Url.class);
    }

    public Url getShortUrlLocations(UrlShort urlShort) throws WeiboClientException {
        return doGet("short_url/locations", withParams(urlShort), Url.class);
    }

    public List<Url> getShortUrlShareCounts(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/share/counts",
                withParams(UrlShort.urlShortParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public Url getShortUrlShareStatuses(UrlShort urlShort) throws WeiboClientException {
        return getShortUrlShareStatuses(urlShort, Paging.EMPTY);
    }

    public Url getShortUrlShareStatuses(UrlShort urlShort, Paging paging) throws WeiboClientException {
        return doGet("short_url/share/statuses",
                paging, withParams(urlShort), Url.class);
    }

    public List<Url> getShortUrlCommentCounts(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/comment/counts",
                withParams(UrlShort.urlShortParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public Url getShortUrlComments(UrlShort urlShort) throws WeiboClientException {
        return getShortUrlComments(urlShort, Paging.EMPTY);
    }

    public Url getShortUrlComments(UrlShort urlShort, Paging paging) throws WeiboClientException {
        return doGet("short_url/comment/comments",
                paging, withParams(urlShort), Url.class);
    }

    public List<UrlInfo> getShortUrlInfo(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlInfoResponse response = doGet("short_url/info",
                withParams(UrlShort.urlShortParam(urlList)), UrlInfoResponse.class);

        return response.getUrls();
    }

    public List<Trend> getTrends(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("trends",
                paging, withParams(uid), Trend.TYPE_TREND_LIST);
    }

    public TrendStatus getTrendStatus(TrendName trendName) throws WeiboClientException {
        return doGet("trends/is_follow",
                withParams(trendName), TrendStatus.class);
    }

    public GlobalTrendList getTrendsHourly() throws WeiboClientException {
        return getTrendsHourly(BaseApp.No);
    }

    public GlobalTrendList getTrendsHourly(BaseApp baseApp) throws WeiboClientException {
        JsonNode json = doGet("trends/hourly",
                withParams(baseApp), JsonNode.class);

        return new GlobalTrendList(json);
    }

    public GlobalTrendList getTrendsDaily() throws WeiboClientException {
        return getTrendsDaily(BaseApp.No);
    }

    public GlobalTrendList getTrendsDaily(BaseApp baseApp) throws WeiboClientException {
        JsonNode json = doGet("trends/daily", withParams(baseApp), JsonNode.class);

        return new GlobalTrendList(json);
    }

    public GlobalTrendList getTrendsWeekly() throws WeiboClientException {
        return getTrendsWeekly(BaseApp.No);
    }

    public GlobalTrendList getTrendsWeekly(BaseApp baseApp) throws WeiboClientException {
        JsonNode json = doGet("trends/weekly", withParams(baseApp), JsonNode.class);

        return new GlobalTrendList(json);
    }

    public long followTrend(TrendName trendName) throws WeiboClientException {
        FollowTrendResponse response = doPost("trends/follow",
                withParams(trendName), FollowTrendResponse.class);

        return response.getTopicid();
    }

    public boolean destroyTrend(TrendId trendId) throws WeiboClientException {
        ResultResponse response = doPost("trends/destroy",
                withParams(trendId), ResultResponse.class);

        return response.isResult();
    }

    public List<Tag> getTags(Uid uid) throws WeiboClientException {
        return getTags(uid, Paging.EMPTY);
    }

    public List<Tag> getTags(Uid uid, Paging paging) throws WeiboClientException {
        ArrayNode arrayNode = doGet("tags", paging, withParams(uid), ArrayNode.class);

        return Tag.parseTags(arrayNode);
    }

    public List<UserTagList> getTagsBatch(Collection<Uid> uids) throws WeiboClientException {
        ArrayNode arrayNode = doGet("tags/tags_batch",
                withParams(Uid.uidsParam(uids)), ArrayNode.class);

        return UserTagList.parse(arrayNode);
    }

    public List<Tag> getTagsSuggestions() throws WeiboClientException {
        return getTagsSuggestions(Paging.EMPTY);
    }

    public List<Tag> getTagsSuggestions(Paging paging) throws WeiboClientException {
        List<LongIdStringValue> list = doGet("tags/suggestions", paging, TYPE_LONG_ID_STRING_VALUE_LIST);

        List<Tag> tags = new ArrayList<Tag>(list.size());
        for (LongIdStringValue value : list) {
            tags.add(new Tag(value.getId(), value.getValue()));
        }

        return tags;
    }

    public List<Long> createTags(Collection<TagName> tagNames) throws WeiboClientException {
        List<TagActionResponse> responseList = doPost("tags/create",
                withParams(TagName.tagsParam(tagNames)), TYPE_TAG_ACTION_RESPONSE_LIST);

        return TagActionResponse.toLongList(responseList);
    }

    public boolean destroyTag(TagId tagId) throws WeiboClientException {
        ResultResponse response = doPost("tags/destroy",
                withParams(tagId), ResultResponse.class);

        return response.isResult();
    }

    public List<Long> destroyTagsBatch(Collection<TagId> tagIds) throws WeiboClientException {
        List<TagActionResponse> responseList = doPost("tags/destroy_batch",
                withParams(TagId.idsParam(tagIds)), TYPE_TAG_ACTION_RESPONSE_LIST);

        return TagActionResponse.toLongList(responseList);
    }

    public VerifyNicknameResult verifyNickname(Nickname nickname) throws WeiboClientException {
        return doGet("register/verify_nickname",
                withParams(nickname), VerifyNicknameResult.class);
    }

    public List<SearchSuggestionUserResult> searchSuggestionUsers(Query query) throws WeiboClientException {
        return searchSuggestionUsers(query, Paging.EMPTY);
    }

    public List<SearchSuggestionUserResult> searchSuggestionUsers(Query query, Paging paging) throws WeiboClientException {
        return doGet("search/suggestions/users",
                paging, withParams(query),
                SearchSuggestionUserResult.TYPE_SEARCH_SUGGESTION_USER_RESULT_LIST);
    }

    public List<SearchSuggestionStatusResult> searchSuggestionStatuses(Query query) throws WeiboClientException {
        return searchSuggestionStatuses(query, Paging.EMPTY);
    }

    public List<SearchSuggestionStatusResult> searchSuggestionStatuses(Query query, Paging paging) throws WeiboClientException {
        return doGet("search/suggestions/statuses",
                paging, withParams(query),
                SearchSuggestionStatusResult.TYPE_SEARCH_SUGGESTION_STATUS_RESULT_LIST);
    }

    public List<SearchSuggestionSchoolResult> searchSuggestionSchools(Query query) throws WeiboClientException {
        return searchSuggestionSchools(query, Paging.EMPTY);
    }

    public List<SearchSuggestionSchoolResult> searchSuggestionSchools(Query query, Paging paging) throws WeiboClientException {
        return searchSuggestionSchools(query, SchoolType.All, paging);
    }

    public List<SearchSuggestionSchoolResult> searchSuggestionSchools(Query query, SchoolType schoolType) throws WeiboClientException {
        return searchSuggestionSchools(query, schoolType, Paging.EMPTY);
    }

    public List<SearchSuggestionSchoolResult> searchSuggestionSchools(Query query, SchoolType schoolType, Paging paging) throws WeiboClientException {
        return doGet("search/suggestions/schools",
                paging, withParams(query, schoolType),
                SearchSuggestionSchoolResult.TYPE_SEARCH_SUGGESTION_SCHOOL_RESULT_LIST);
    }

    public List<SearchSuggestionCompanyResult> searchSuggestionCompanies(Query query) throws WeiboClientException {
        return searchSuggestionCompanies(query, Paging.EMPTY);
    }

    public List<SearchSuggestionCompanyResult> searchSuggestionCompanies(Query query, Paging paging) throws WeiboClientException {
        return doGet("search/suggestions/companies",
                paging, withParams(query),
                SearchSuggestionCompanyResult.TYPE_SEARCH_SUGGESTION_COMPANY_RESULT_LIST);
    }

    public List<SearchSuggestionAppResult> searchSuggestionApps(Query query) throws WeiboClientException {
        return searchSuggestionApps(query, Paging.EMPTY);
    }

    public List<SearchSuggestionAppResult> searchSuggestionApps(Query query, Paging paging) throws WeiboClientException {
        return doGet("search/suggestions/apps",
                paging, withParams(query),
                SearchSuggestionAppResult.TYPE_SEARCH_SUGGESTION_APP_RESULT_LIST);
    }

    public List<SearchSuggestionAtUserResult> searchSuggestionAtUsers(Query query, SuggestionType type)
            throws WeiboClientException {
        return searchSuggestionAtUsers(query, type, SuggestionRange.All);
    }

    public List<SearchSuggestionAtUserResult> searchSuggestionAtUsers(Query query, SuggestionType type, Paging paging)
            throws WeiboClientException {
        return searchSuggestionAtUsers(query, type, SuggestionRange.All, paging);
    }

    public List<SearchSuggestionAtUserResult> searchSuggestionAtUsers(Query query, SuggestionType type, SuggestionRange range)
            throws WeiboClientException {
        return searchSuggestionAtUsers(query, type, range, Paging.EMPTY);
    }

    public List<SearchSuggestionAtUserResult> searchSuggestionAtUsers(Query query, SuggestionType type,
                                                                      SuggestionRange range, Paging paging) throws WeiboClientException {
        return doGet("search/suggestions/at_users",
                paging, withParams(query, type, range),
                SearchSuggestionAtUserResult.TYPE_SEARCH_SUGGESTION_AT_USER_RESULT_LIST);
    }

    public Timeline searchTopics(Query query) throws WeiboClientException {
        return searchTopics(query, Paging.EMPTY);
    }

    public Timeline searchTopics(Query query, Paging paging) throws WeiboClientException {
        return doGet("search/topics",
                paging, withParams(query), Timeline.class);
    }

    public List<User> getSuggestionUsersHot() throws WeiboClientException {
        return getSuggestionUsersHot(SuggestionUserCategory.Default);
    }

    public List<User> getSuggestionUsersHot(SuggestionUserCategory category) throws WeiboClientException {
        return doGet("suggestions/users/hot",
                withParams(category), User.TYPE_USER_LIST);
    }

    // TODO: implements getSuggestionUsersMayInterested
//    public ArrayNode getSuggestionUsersMayInterested(Paging paging) throws WeiboClientException {
//        return doGet("suggestions/users/may_interested", paging, ArrayNode.class);
//    }

    public UserList getSuggestionUsersByStatus(Content content) throws WeiboClientException {
        return getSuggestionUsersByStatus(content, Num.EMPTY);
    }

    public UserList getSuggestionUsersByStatus(Content content, Num num) throws WeiboClientException {
        return doGet("suggestions/users/by_status",
                withParams(content, num), UserList.class);
    }

    public StatusList getSuggestionStatusesHot(SuggestionStatusType type, IsPic isPic) throws WeiboClientException {
        return getSuggestionStatusesHot(type, isPic, Paging.EMPTY);
    }

    public StatusList getSuggestionStatusesHot(SuggestionStatusType type, IsPic isPic, Paging paging) throws WeiboClientException {
        return doGet("suggestions/statuses/hot",
                paging, withParams(type, isPic), StatusList.class);
    }

    public StatusList getSuggestionStatusesReorder(Section section) throws WeiboClientException {
        return getSuggestionStatusesReorder(section, Paging.EMPTY);
    }

    public StatusList getSuggestionStatusesReorder(Section section, Paging paging) throws WeiboClientException {
        return doGet("suggestions/statuses/reorder",
                paging, withParams(section), StatusList.class);
    }

    public StatusIdList getSuggestionStatusIdsReorder(Section section) throws WeiboClientException {
        return getSuggestionStatusIdsReorder(section, Paging.EMPTY);
    }

    public StatusIdList getSuggestionStatusIdsReorder(Section section, Paging paging) throws WeiboClientException {
        return doGet("suggestions/statuses/reorder/ids",
                paging, withParams(section), StatusIdList.class);
    }

    public List<Status> getSuggestionFavoritesHot() throws WeiboClientException {
        return getSuggestionFavoritesHot(Paging.EMPTY);
    }

    public List<Status> getSuggestionFavoritesHot(Paging paging) throws WeiboClientException {
        return doGet("suggestions/favorites/hot", paging, Status.TYPE_STATUS_LIST);
    }

    public User setSuggestionUserNotInterested(Uid uid) throws WeiboClientException {
        return doPost("suggestions/users/not_interested",
                withParams(uid), User.class);
    }

    public UnreadCount getUnreadCount() throws WeiboClientException {
        Uid uid = new Uid(getAccountUid());
        return getUnreadCount(uid);
    }

    public UnreadCount getUnreadCount(Uid uid) throws WeiboClientException {
        return doGet("remind/unread_count", withParams(uid), UnreadCount.class);
    }

    public boolean resetCount(CountType type) throws WeiboClientException {
        ResultResponse response = doPost("https://rm.api.weibo.com/2/remind/set_count.json",
                withParams(type), ResultResponse.class);

        return response.isResult();
    }

    public NotificationResult sendNotification(Collection<Uid> uids, TemplateId templateId) throws WeiboClientException {
        return sendNotification(uids, templateId, null);
    }

    public NotificationResult sendNotification(Collection<Uid> uids, TemplateId templateId, ActionUrl actionUrl)
            throws WeiboClientException {
        return sendNotification(uids, templateId, null, 0, actionUrl);
    }

    public NotificationResult sendNotification(Collection<Uid> uids,
                                               TemplateId templateId,
                                               final String objects1,
                                               final int objects1Count) throws WeiboClientException {
        return sendNotification(uids, templateId, objects1, objects1Count, null, 0);
    }

    public NotificationResult sendNotification(Collection<Uid> uids,
                                               TemplateId templateId,
                                               final String objects1,
                                               final int objects1Count,
                                               final String objects2,
                                               final int objects2Count) throws WeiboClientException {
        return sendNotification(uids, templateId, objects1, objects1Count, objects2, objects2Count, null, 0);
    }

    public NotificationResult sendNotification(Collection<Uid> uids,
                                               TemplateId templateId,
                                               final String objects1,
                                               final int objects1Count,
                                               final String objects2,
                                               final int objects2Count,
                                               final String objects3,
                                               final int objects3Count) throws WeiboClientException {
        return sendNotification(uids, templateId, objects1, objects1Count, objects2, objects2Count, objects3, objects3Count, ActionUrl.EMPTY);
    }

    public NotificationResult sendNotification(Collection<Uid> uids,
                                               TemplateId templateId,
                                               final String objects1,
                                               final int objects1Count,
                                               ActionUrl actionUrl) throws WeiboClientException {
        return sendNotification(uids, templateId, objects1, objects1Count, null, 0, actionUrl);
    }

    public NotificationResult sendNotification(Collection<Uid> uids,
                                               TemplateId templateId,
                                               final String objects1,
                                               final int objects1Count,
                                               final String objects2,
                                               final int objects2Count,
                                               ActionUrl actionUrl) throws WeiboClientException {
        return sendNotification(uids, templateId, objects1, objects1Count, objects2, objects2Count, null, 0, actionUrl);
    }

    public NotificationResult sendNotification(Collection<Uid> uids,
                                               TemplateId templateId,
                                               final String objects1,
                                               final int objects1Count,
                                               final String objects2,
                                               final int objects2Count,
                                               final String objects3,
                                               final int objects3Count,
                                               ActionUrl actionUrl) throws WeiboClientException {
        ParameterAction objectsParamAction = new ParameterAction() {
            public void addParameter(Parameters params) {
                if (StringUtils.isNotBlank(objects1)) {
                    params.add("objects1", objects1);
                }

                if (objects1Count > 0) {
                    params.add("objects1_count", objects1Count);
                }

                if (StringUtils.isNotBlank(objects2)) {
                    params.add("objects2", objects2);
                }

                if (objects2Count > 0) {
                    params.add("objects2_count", objects2Count);
                }

                if (StringUtils.isNotBlank(objects3)) {
                    params.add("objects3", objects3);
                }

                if (objects3Count > 0) {
                    params.add("objects3_count", objects3Count);
                }
            }
        };

        return doPost("notification/send",
                withParams(Uid.uidsParam(uids), templateId, objectsParamAction, actionUrl), NotificationResult.class);
    }

    public Map<String, String> getTimezoneMap() throws WeiboClientException {
        return getTimezoneMap(null);
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getTimezoneMap(Language language) throws WeiboClientException {
        return doGet("common/get_timezone",
                withParams(language), HashMap.class);
    }

    public Map<String, String> getCountryMap() throws WeiboClientException {
        return getCountryMap(null, null);
    }

    public Map<String, String> getCountryMap(Language language, CapitalLetter capitalLetter) throws WeiboClientException {
        List<Map<String, String>> response = doGet("common/get_country",
                withParams(language, capitalLetter), LIST_MAP_S_S_TYPE_REFERENCE);

        return mergeSingleItemMap(response);
    }

    public Map<String, String> getProvinceMap(Country country) throws WeiboClientException {
        return getProvinceMap(country, null, null);
    }

    public Map<String, String> getProvinceMap(Country country, Language language, CapitalLetter capitalLetter) throws WeiboClientException {
        List<Map<String, String>> response = doGet("common/get_province",
                withParams(country, language, capitalLetter), LIST_MAP_S_S_TYPE_REFERENCE);

        return mergeSingleItemMap(response);
    }

    public Map<String, String> getCityMap(Province province) throws WeiboClientException {
        return getCityMap(province, null, null);
    }

    public Map<String, String> getCityMap(Province province, Language language, CapitalLetter capitalLetter) throws WeiboClientException {
        List<Map<String, String>> response = doGet("common/get_city",
                withParams(province, language, capitalLetter), LIST_MAP_S_S_TYPE_REFERENCE);

        return mergeSingleItemMap(response);
    }

    public Map<String, String> codeToLocation(Collection<AddressCode> codes) throws WeiboClientException {
        List<Map<String, String>> response = doGet("common/code_to_location",
                withParams(AddressCode.codesParam(codes)), LIST_MAP_S_S_TYPE_REFERENCE);

        return mergeSingleItemMap(response);
    }

    public static Parameters withParams(ParameterAction... actions) {
        Parameters params = Parameters.create();

        for (ParameterAction action : actions) {
            if (action != null) {
                action.addParameter(params);
            }
        }

        return params;
    }

    public <T> T doGet(String path, Paging paging, Parameters params, Class<T> clazz) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), paging, params, clazz);
    }

    public <T> T doGet(String path, Parameters params, Class<T> clazz) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), params, clazz);
    }

    public <T> T doGet(String path, Paging paging, Class<T> clazz) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), paging, clazz);
    }

    public <T> T doGet(String path, Class<T> clazz) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), clazz);
    }

    public <T> List<T> doGet(String path, TypeReference<List<T>> typeReference) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), typeReference);
    }

    public <T> List<T> doGet(String path, Paging paging, TypeReference<List<T>> typeReference) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), paging, typeReference);
    }

    public <T> List<T> doGet(String path, Parameters params, TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), params, typeReference);
    }

    public <T> List<T> doGet(String path, Paging paging, Parameters params,
                             TypeReference<List<T>> typeReference) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), paging, params, typeReference);
    }

    public <T> T doPost(String path, Paging paging, Parameters params, Class<T> clazz) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createPostRequest(path), paging, params, clazz);
    }

    public <T> T doPost(String path, Parameters params, Class<T> clazz) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createPostRequest(path), params, clazz);
    }

    public <T> List<T> doPost(String path, Parameters params, TypeReference<List<T>> typeReference) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createPostRequest(path), params, typeReference);
    }

    public <T> List<T> sendRequestAndGetResponseObject(OAuthRequest request, Parameters params,
                                                       TypeReference<List<T>> typeReference) throws WeiboClientException {
        return sendRequestAndGetResponseObject(request, Paging.EMPTY, params, typeReference);
    }

    public <T> List<T> sendRequestAndGetResponseObject(OAuthRequest request, Paging paging,
                                                       TypeReference<List<T>> typeReference) throws WeiboClientException {
        return sendRequestAndGetResponseObject(request, paging, Parameters.create(), typeReference);
    }

    public <T> List<T> sendRequestAndGetResponseObject(OAuthRequest request, Paging paging, Parameters params,
                                                       TypeReference<List<T>> typeReference) throws WeiboClientException {
        if (paging != null) {
            params.add(paging);
        }

        params.appendTo(request);

        return sendRequestAndGetResponseObject(request, typeReference);
    }

    public <T> List<T> sendRequestAndGetResponseObject(OAuthRequest request, TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        Response response = request.send();

        return parseJsonObject(response, typeReference);
    }

    public <T> T sendRequestAndGetResponseObject(OAuthRequest request, Parameters params, Class<T> clazz)
            throws WeiboClientException {
        return sendRequestAndGetResponseObject(request, Paging.EMPTY, params, clazz);
    }

    public <T> T sendRequestAndGetResponseObject(OAuthRequest request, Paging paging, Class<T> clazz)
            throws WeiboClientException {
        return sendRequestAndGetResponseObject(request, paging, Parameters.create(), clazz);
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
        setRequestTimeout(request);

        if (accessToken != null) {
            request.addQuerystringParameter(ACCESS_TOKEN, accessToken.getToken());
        }

        return request;
    }

    public OAuthRequest createPostRequest(String path) {
        OAuthRequest request = new OAuthRequest(Verb.POST, getFullPath(path));
        setRequestTimeout(request);

        if (accessToken != null) {
            request.addBodyParameter(ACCESS_TOKEN, accessToken.getToken());
        }

        return request;
    }

    private void setRequestTimeout(OAuthRequest request) {
        request.setConnectTimeout(connectTimeoutDuration, connectTimeoutUnit);
        request.setReadTimeout(readTimeoutDuration, readTimeoutUnit);
    }

    public String getFullPath(String path) {
        if (path.startsWith("http://") || path.startsWith("https://")) {
            return path;
        } else {
            return API2_URL + path + ".json";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class TagActionResponse {
        private long tagid;

        public long getTagid() {
            return tagid;
        }

        public void setTagid(long tagid) {
            this.tagid = tagid;
        }

        public static List<Long> toLongList(List<TagActionResponse> responseList) {
            List<Long> result = new ArrayList<Long>(responseList.size());
            for (TagActionResponse response : responseList) {
                result.add(response.getTagid());
            }

            return result;
        }
    }

    private static final TypeReference<List<TagActionResponse>> TYPE_TAG_ACTION_RESPONSE_LIST =
            new TypeReference<List<TagActionResponse>>() {
            };

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class LongIdStringValue {
        private long id;
        private String value;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    private static final TypeReference<List<LongIdStringValue>> TYPE_LONG_ID_STRING_VALUE_LIST =
            new TypeReference<List<LongIdStringValue>>() {
            };

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class FollowTrendResponse {
        private long topicid;

        public long getTopicid() {
            return topicid;
        }

        public void setTopicid(long topicid) {
            this.topicid = topicid;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class UrlInfoResponse {
        private List<UrlInfo> urls;

        public List<UrlInfo> getUrls() {
            return urls;
        }

        public void setUrls(List<UrlInfo> urls) {
            this.urls = urls;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class UrlResponse {
        private List<Url> urls;

        public List<Url> getUrls() {
            return urls;
        }

        public void setUrls(List<Url> urls) {
            this.urls = urls;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ResultResponse {
        private boolean result;

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }
    }

    private static SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class RawRateLimitStatus {
        private int ipLimit;
        private String limitTimeUnit;
        private int remainingIpHits;
        private int remainingUserHits;
        private String resetTime;
        private int resetTimeInSeconds;
        private int userLimit;

        public RateLimitStatus asRateLimitStatus() throws ParseException {
            RateLimitStatus status = new RateLimitStatus();
            status.setIpLimit(ipLimit);
            status.setLimitTimeUnit(limitTimeUnit);
            status.setRemainingUserHits(remainingUserHits);
            status.setResetTimeInSeconds(resetTimeInSeconds);
            status.setUserLimit(userLimit);

            status.setResetTime(simpleFormat.parse(resetTime));

            return status;
        }

        public int getIpLimit() {
            return ipLimit;
        }

        public void setIpLimit(int ipLimit) {
            this.ipLimit = ipLimit;
        }

        public String getLimitTimeUnit() {
            return limitTimeUnit;
        }

        public void setLimitTimeUnit(String limitTimeUnit) {
            this.limitTimeUnit = limitTimeUnit;
        }

        public int getRemainingIpHits() {
            return remainingIpHits;
        }

        public void setRemainingIpHits(int remainingIpHits) {
            this.remainingIpHits = remainingIpHits;
        }

        public int getRemainingUserHits() {
            return remainingUserHits;
        }

        public void setRemainingUserHits(int remainingUserHits) {
            this.remainingUserHits = remainingUserHits;
        }

        public String getResetTime() {
            return resetTime;
        }

        public void setResetTime(String resetTime) {
            this.resetTime = resetTime;
        }

        public int getResetTimeInSeconds() {
            return resetTimeInSeconds;
        }

        public void setResetTimeInSeconds(int resetTimeInSeconds) {
            this.resetTimeInSeconds = resetTimeInSeconds;
        }

        public int getUserLimit() {
            return userLimit;
        }

        public void setUserLimit(int userLimit) {
            this.userLimit = userLimit;
        }
    }

    public static final TypeReference<List<Map<String, String>>> LIST_MAP_S_S_TYPE_REFERENCE = new TypeReference<List<Map<String, String>>>() {
    };

    private Map<String, String> mergeSingleItemMap(List<Map<String, String>> response) {
        Map<String, String> map = new HashMap<String, String>();
        for (Map<String, String> item : response) {
            map.putAll(item);
        }

        return map;
    }

    private ParameterAction urlParam(final URL url) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (url != null) {
                    params.add("url", url.toExternalForm());
                }
            }
        };
    }
}
