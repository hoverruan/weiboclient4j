package weiboclient4j;

import org.codehaus.jackson.JsonNode;
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
import weiboclient4j.model.Privacy;
import weiboclient4j.model.RateLimitStatus;
import weiboclient4j.model.RepostTimeline;
import weiboclient4j.model.Status;
import weiboclient4j.model.Tag;
import weiboclient4j.model.Timeline;
import weiboclient4j.model.TimelineIds;
import weiboclient4j.model.Trend;
import weiboclient4j.model.TrendStatus;
import weiboclient4j.model.Url;
import weiboclient4j.model.UrlInfo;
import weiboclient4j.model.User;
import weiboclient4j.model.UserCount;
import weiboclient4j.model.UserIdList;
import weiboclient4j.model.UserList;
import weiboclient4j.oauth2.DisplayType;
import weiboclient4j.oauth2.GrantType;
import weiboclient4j.oauth2.ResponseType;
import weiboclient4j.oauth2.SinaWeibo2AccessToken;
import weiboclient4j.oauth2.SinaWeibo2Api;
import weiboclient4j.params.BaseApp;
import weiboclient4j.params.Cid;
import weiboclient4j.params.CommentOri;
import weiboclient4j.params.Feature;
import weiboclient4j.params.FilterByAuthor;
import weiboclient4j.params.FilterBySource;
import weiboclient4j.params.FilterByType;
import weiboclient4j.params.Id;
import weiboclient4j.params.InboxType;
import weiboclient4j.params.IsBase62;
import weiboclient4j.params.IsBatch;
import weiboclient4j.params.IsComment;
import weiboclient4j.params.Latitude;
import weiboclient4j.params.Longitude;
import weiboclient4j.params.Mid;
import weiboclient4j.params.MidType;
import weiboclient4j.params.Paging;
import weiboclient4j.params.ParameterAction;
import weiboclient4j.params.Parameters;
import weiboclient4j.params.Remark;
import weiboclient4j.params.ScreenName;
import weiboclient4j.params.SourceScreenName;
import weiboclient4j.params.SourceUid;
import weiboclient4j.params.Suid;
import weiboclient4j.params.TagName;
import weiboclient4j.params.TargetScreenName;
import weiboclient4j.params.TargetUid;
import weiboclient4j.params.Tid;
import weiboclient4j.params.TrendId;
import weiboclient4j.params.TrendName;
import weiboclient4j.params.TrimUser;
import weiboclient4j.params.Uid;
import weiboclient4j.params.UrlLong;
import weiboclient4j.params.UrlShort;
import weiboclient4j.params.WithoutMention;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;
import static weiboclient4j.utils.StringUtils.isNotBlank;
import static weiboclient4j.utils.StringUtils.join;

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
    public static final String MID = "mid";
    public static final String INBOX = "inbox";
    public static final String IS_BASE_62 = "isBase62";
    public static final String IDS = "ids";
    public static final String STATUS = "status";
    public static final String IS_COMMENT = "is_comment";
    public static final String LONGITUDE = "long";
    public static final String LATITUDE = "lat";
    public static final String URL_ = "url";
    public static final String CIDS = "cids";
    public static final String COMMENT = "comment";
    public static final String COMMENT_ORI = "comment_ori";
    public static final String CID = "cid";
    public static final String DOMAIN = "domain";
    public static final String UIDS = "uids";
    public static final String SOURCE_ID = "source_id";
    public static final String SOURCE_SCREEN_NAME = "source_screen_name";
    public static final String TARGET_ID = "target_id";
    public static final String TARGET_SCREEN_NAME = "target_screen_name";
    public static final String SUID = "suid";
    public static final String WITHOUT_MENTION = "without_mention";
    public static final String REMARK = "remark";
    public static final String TID = "tid";
    public static final String TAGS = "tags";
    public static final String TAG = "tag";
    public static final String URL_LONG = "url_long";
    public static final String URL_SHORT = "url_short";
    public static final String TREND_NAME = "trend_name";
    public static final String TREND_ID = "trend_id";

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
                paging,
                withParams(
                        baseAppParam(baseApp)),
                Timeline.class);
    }

    public Timeline getFriendsTimeline() throws WeiboClientException {
        return getFriendsTimeline(Paging.EMPTY);
    }

    public Timeline getFriendsTimeline(Paging paging) throws WeiboClientException {
        return getFriendsTimeline(paging, BaseApp.No, Feature.All);
    }

    public Timeline getFriendsTimeline(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        return doGet("statuses/friends_timeline",
                paging,
                withParams(
                        baseAppParam(baseApp),
                        featureParam(feature)),
                Timeline.class);
    }

    public Timeline getHomeTimeline() throws WeiboClientException {
        return getHomeTimeline(Paging.EMPTY);
    }

    public Timeline getHomeTimeline(Paging paging) throws WeiboClientException {
        return getHomeTimeline(paging, BaseApp.No, Feature.All);
    }

    public Timeline getHomeTimeline(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        return doGet("statuses/home_timeline",
                paging,
                withParams(
                        baseAppParam(baseApp),
                        featureParam(feature)),
                Timeline.class);
    }

    public TimelineIds getFriendsTimelineIds() throws WeiboClientException {
        return getFriendsTimelineIds(Paging.EMPTY);
    }

    public TimelineIds getFriendsTimelineIds(Paging paging) throws WeiboClientException {
        return getFriendsTimelineIds(paging, BaseApp.No, Feature.All);
    }

    public TimelineIds getFriendsTimelineIds(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        return doGet("statuses/friends_timeline/ids",
                paging,
                withParams(
                        baseAppParam(baseApp),
                        featureParam(feature)),
                TimelineIds.class);
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

    public Timeline getUserTimeline(Paging paging, BaseApp baseApp, Feature feature, TrimUser trimUser)
            throws WeiboClientException {
        return getUserTimeline(Uid.EMPTY, ScreenName.EMPTY, paging, baseApp, feature, trimUser);
    }

    public Timeline getUserTimeline(Uid uid, ScreenName screenName, Paging paging, BaseApp baseApp,
                                    Feature feature, TrimUser trimUser) throws WeiboClientException {
        return doGet("statuses/user_timeline",
                paging,
                withParams(
                        uidParam(uid),
                        screenNameParam(screenName),
                        trimUserParam(trimUser),
                        baseAppParam(baseApp),
                        featureParam(feature)),
                Timeline.class);
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
                paging,
                withParams(
                        uidParam(uid),
                        screenNameParam(screenName),
                        baseAppParam(baseApp),
                        featureParam(feature)),
                TimelineIds.class);
    }

    public RepostTimeline getRepostTimeline(Id id) throws WeiboClientException {
        return getRepostTimeline(id, Paging.EMPTY);
    }

    public RepostTimeline getRepostTimeline(Id id, Paging paging) throws WeiboClientException {
        return getRepostTimeline(id, paging, FilterByAuthor.All);
    }

    public RepostTimeline getRepostTimeline(Id id, Paging paging, FilterByAuthor filterByAuthor) throws WeiboClientException {
        return doGet("statuses/repost_timeline",
                paging,
                withParams(
                        idParam(id),
                        filterByAuthorParam(filterByAuthor)),
                RepostTimeline.class);
    }

    public TimelineIds getRepostTimelineIds(Id id) throws WeiboClientException {
        return getRepostTimelineIds(id, Paging.EMPTY);
    }

    public TimelineIds getRepostTimelineIds(Id id, Paging paging) throws WeiboClientException {
        return getRepostTimelineIds(id, paging, FilterByAuthor.All);
    }

    public TimelineIds getRepostTimelineIds(Id id, Paging paging, FilterByAuthor filterByAuthor) throws WeiboClientException {
        return doGet("statuses/repost_timeline/ids",
                paging,
                withParams(
                        idParam(id),
                        filterByAuthorParam(filterByAuthor)),
                TimelineIds.class);
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
                paging,
                withParams(
                        filterByAuthorParam(filterByAuthor),
                        filterBySourceParam(filterBySource),
                        filterByTypeParam(filterByType)),
                Timeline.class);
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
                paging,
                withParams(
                        filterByAuthorParam(filterByAuthor),
                        filterBySourceParam(filterBySource),
                        filterByTypeParam(filterByType)),
                TimelineIds.class);
    }

    public Timeline getBilateralTimeline() throws WeiboClientException {
        return getBilateralTimeline(Paging.EMPTY);
    }

    public Timeline getBilateralTimeline(Paging paging) throws WeiboClientException {
        return getBilateralTimeline(paging, BaseApp.No, Feature.All);
    }

    public Timeline getBilateralTimeline(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        return doGet("statuses/bilateral_timeline",
                paging,
                withParams(
                        baseAppParam(baseApp),
                        featureParam(feature)),
                Timeline.class);
    }

    public Status showStatus(Id id) throws WeiboClientException {
        return doGet("statuses/show",
                withParams(
                        idParam(id)),
                Status.class);
    }

    public String queryMid(Id id, MidType midType) throws WeiboClientException {
        MidResponse midResponse = doGet("statuses/querymid",
                withParams(
                        idParam(id),
                        midTypeParam(midType)),
                MidResponse.class);

        return midResponse != null ? midResponse.getMid() : null;
    }

    public Map<Long, String> queryMidList(Collection<Id> idList, MidType midType) throws WeiboClientException {
        // [{"3436240135184587":"yfcLPlKKn"},{"3436255091659029":"yfd9X6XAx"}]
        ArrayNode arrayNode = doGet("statuses/querymid",
                withParams(
                        idListParam(idList),
                        midTypeParam(midType),
                        isBase62Param(IsBase62.Yes)),
                ArrayNode.class);
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
                withParams(
                        midParam(mid),
                        midTypeParam(type),
                        inboxTypeParam(inboxType),
                        isBase62Param(isBase62)),
                IdResponse.class);

        return idResponse.getId();
    }

    public Map<String, Long> queryIdList(Collection<Mid> midList, MidType type, IsBase62 isBase62) throws WeiboClientException {
        return queryIdList(midList, type, null, isBase62);
    }

    public Map<String, Long> queryIdList(Collection<Mid> midList, MidType type, InboxType inboxType, IsBase62 isBase62)
            throws WeiboClientException {
        // [{"yfcLPlKKn":"3436240135184587"},{"yfd9X6XAx":"3436255091659029"}]
        ArrayNode arrayNode = doGet("statuses/queryid",
                withParams(
                        midListParam(midList),
                        midTypeParam(type),
                        inboxTypeParam(inboxType),
                        isBase62Param(isBase62),
                        isBatchParam(IsBatch.Yes)),
                ArrayNode.class);
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
                paging,
                withParams(
                        baseAppParam(baseApp)),
                WeiboClient.TYPE_STATUS_LIST);
    }

    public List<Status> getHotRepostWeekly() throws WeiboClientException {
        return getHotRepostWeekly(Paging.EMPTY);
    }

    public List<Status> getHotRepostWeekly(Paging paging) throws WeiboClientException {
        return getHotRepostWeekly(paging, BaseApp.No);
    }

    public List<Status> getHotRepostWeekly(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return doGet("statuses/hot/repost_weekly",
                paging,
                withParams(
                        baseAppParam(baseApp)),
                WeiboClient.TYPE_STATUS_LIST);
    }

    public List<Status> getHotCommentsDaily() throws WeiboClientException {
        return getHotCommentsDaily(Paging.EMPTY);
    }

    public List<Status> getHotCommentsDaily(Paging paging) throws WeiboClientException {
        return getHotCommentsDaily(paging, BaseApp.No);
    }

    public List<Status> getHotCommentsDaily(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return doGet("statuses/hot/comments_daily",
                paging,
                withParams(
                        baseAppParam(baseApp)),
                WeiboClient.TYPE_STATUS_LIST);
    }

    public List<Status> getHotCommentsWeekly() throws WeiboClientException {
        return getHotCommentsWeekly(Paging.EMPTY);
    }

    public List<Status> getHotCommentsWeekly(Paging paging) throws WeiboClientException {
        return getHotCommentsWeekly(paging, BaseApp.No);
    }

    public List<Status> getHotCommentsWeekly(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return doGet("statuses/hot/comments_weekly",
                paging,
                withParams(baseAppParam(baseApp)),
                WeiboClient.TYPE_STATUS_LIST);
    }

    public List<Count> getStatusesCounts(Collection<Id> ids) throws WeiboClientException {
        return doGet("statuses/count",
                withParams(
                        idsParam(ids)),
                WeiboClient.TYPE_COUNT_LIST);
    }

    public Status repostStatus(Id id, String status) throws WeiboClientException {
        return repostStatus(id, status, IsComment.No);
    }

    public Status repostStatus(Id id, String status, IsComment isComment) throws WeiboClientException {
        return doPost("statuses/repost",
                withParams(
                        idParam(id),
                        statusParam(status),
                        isCommentParam(isComment)),
                Status.class);
    }

    public Status updateStatus(String status) throws WeiboClientException {
        return updateStatus(status, null, null);
    }

    public Status updateStatus(String status, Latitude latitude, Longitude longitude) throws WeiboClientException {
        return doPost("statuses/update",
                withParams(
                        statusParam(status),
                        latitudeParam(latitude),
                        longitudeParam(longitude)),
                Status.class);
    }

    public Status destroyStatus(Id id) throws WeiboClientException {
        return doPost("statuses/destroy",
                withParams(
                        idParam(id)),
                Status.class);
    }

    public Status uploadImageUrl(String status, URL url) throws WeiboClientException {
        return uploadImageUrl(status, url, null, null);
    }

    public Status uploadImageUrl(String status, URL url, Latitude latitude, Longitude longitude) throws WeiboClientException {
        return doPost("statuses/upload_url_text",
                withParams(
                        statusParam(status),
                        urlParam(url),
                        latitudeParam(latitude),
                        longitudeParam(longitude)),
                Status.class);
    }

//    TODO implements update binary image
//    public Status uploadImageBinary() {
//
//    }

    public List<Emotion> getEmotions() throws WeiboClientException {
        return doGet("emotions", WeiboClient.TYPE_EMOTION_LIST);
    }

    public CommentList getComments(Id id) throws WeiboClientException {
        return getComments(id, Paging.EMPTY);
    }

    public CommentList getComments(Id id, Paging paging) throws WeiboClientException {
        return getComments(id, paging, FilterByAuthor.All);
    }

    public CommentList getComments(Id id, Paging paging, FilterByAuthor filterByAuthor) throws WeiboClientException {
        return doGet("comments/show",
                paging,
                withParams(
                        idParam(id),
                        filterByAuthorParam(filterByAuthor)),
                CommentList.class);
    }

    public CommentList getCommentsByMe() throws WeiboClientException {
        return getCommentsByMe(Paging.EMPTY);
    }

    public CommentList getCommentsByMe(Paging paging) throws WeiboClientException {
        return getCommentsByMe(paging, FilterByAuthor.All);
    }

    public CommentList getCommentsByMe(Paging paging, FilterByAuthor filterByAuthor) throws WeiboClientException {
        return doGet("comments/by_me",
                paging,
                withParams(
                        filterByAuthorParam(filterByAuthor)),
                CommentList.class);
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
                paging,
                withParams(
                        filterByAuthorParam(filterByAuthor),
                        filterBySourceParam(filterBySource)),
                CommentList.class);
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
                paging,
                withParams(
                        filterByAuthorParam(filterByAuthor),
                        filterBySourceParam(filterBySource)),
                CommentList.class);
    }

    public List<Comment> getCommentsBatch(Collection<Cid> cids) throws WeiboClientException {
        return doGet("comments/show_batch",
                withParams(cidsParam(cids)),
                WeiboClient.TYPE_COMMENT_LIST);
    }

    public Comment createComment(Id id, String comment) throws WeiboClientException {
        return createComment(id, comment, CommentOri.No);
    }

    public Comment createComment(Id id, String comment, CommentOri commentOri) throws WeiboClientException {
        return doPost("comments/create",
                withParams(
                        idParam(id),
                        commentParam(comment),
                        commentOriParam(commentOri)),
                Comment.class);
    }

    public Comment destroyComment(Cid cid) throws WeiboClientException {
        return doPost("comments/destroy",
                withParams(
                        cidParam(cid)),
                Comment.class);
    }

    public List<Comment> destroyCommentBatch(Collection<Cid> cids) throws WeiboClientException {
        List<Id> ids = new ArrayList<Id>(cids.size());
        for (Cid cid : cids) {
            ids.add(new Id(cid.getValue()));
        }

        return doPost("comments/destroy_batch",
                withParams(
                        idsParam(ids)),
                WeiboClient.TYPE_COMMENT_LIST);
    }

    public Comment replyComment(Id id, Cid cid, String comment) throws WeiboClientException {
        return replyComment(id, cid, comment, WithoutMention.No, CommentOri.No);
    }

    public Comment replyComment(Id id, Cid cid, String comment, WithoutMention withoutMention, CommentOri commentOri)
            throws WeiboClientException {
        return doPost("comments/reply",
                withParams(
                        idParam(id),
                        cidParam(cid),
                        commentParam(comment),
                        withoutMentionParam(withoutMention),
                        commentOriParam(commentOri)),
                Comment.class);
    }

    public User showUser(ScreenName screenName) throws WeiboClientException {
        return showUser(Uid.EMPTY, screenName);
    }

    public User showUser(Uid uid) throws WeiboClientException {
        return showUser(uid, ScreenName.EMPTY);
    }

    private User showUser(Uid uid, ScreenName screenName) throws WeiboClientException {
        return doGet("users/show",
                withParams(
                        uidParam(uid),
                        screenNameParam(screenName)),
                User.class);
    }

    public User showUserByDomain(String domain) throws WeiboClientException {
        return doGet("users/domain_show",
                withParams(
                        domainParam(domain)),
                User.class);
    }

    public List<UserCount> getUsersCounts(Collection<Uid> uids) throws WeiboClientException {
        return doGet("users/counts",
                withParams(
                        uidsParam(uids)),
                UserCount.LIST_TYPE);
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
                paging,
                withParams(
                        uidParam(uid),
                        screenNameParam(screenName)),
                UserList.class);
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
                paging,
                withParams(
                        uidParam(uid),
                        screenNameParam(screenName)),
                UserIdList.class);
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
                paging,
                withParams(
                        uidParam(uid),
                        suidParam(suid)),
                UserList.class);
    }

    public UserList getFriendsBilateral(Uid uid) throws WeiboClientException {
        return getFriendsBilateral(uid, Paging.EMPTY);
    }

    public UserList getFriendsBilateral(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends/bilateral",
                paging,
                withParams(
                        uidParam(uid)),
                UserList.class);
    }

    public UserIdList getFriendsBilateralIds(Uid uid) throws WeiboClientException {
        return getFriendsBilateralIds(uid, Paging.EMPTY);
    }

    public UserIdList getFriendsBilateralIds(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends/bilateral/ids",
                paging,
                withParams(
                        uidParam(uid)),
                UserIdList.class);
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
                paging,
                withParams(
                        uidParam(uid),
                        screenNameParam(screenName)),
                UserList.class);
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
                paging,
                withParams(
                        uidParam(uid),
                        screenNameParam(screenName)),
                UserIdList.class);
    }

    public UserList getActiveFollowers(Uid uid) throws WeiboClientException {
        return getActiveFollowers(uid, Paging.EMPTY);
    }

    public UserList getActiveFollowers(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("friendships/followers/active",
                paging,
                withParams(
                        uidParam(uid)),
                UserList.class);
    }

    public UserList getChainFollowers(Uid uid) throws WeiboClientException {
        return getChainFollowers(uid, Paging.EMPTY);
    }

    public UserList getChainFollowers(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends_chain/followers",
                paging,
                withParams(
                        uidParam(uid)),
                UserList.class);
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
                withParams(
                        sourceUidParam(sourceUid),
                        sourceScreenNameParam(sourceScreenName),
                        targetUidParam(targetUid),
                        targetScreenNameParam(targetScreenName)),
                Friendship.class);
    }

    public User createFriendship(Uid uid) throws WeiboClientException {
        return createFriendship(uid, ScreenName.EMPTY);
    }

    public User createFriendship(ScreenName screenName) throws WeiboClientException {
        return createFriendship(Uid.EMPTY, screenName);
    }

    public User createFriendship(Uid uid, ScreenName screenName) throws WeiboClientException {
        return doPost("friendships/create",
                withParams(
                        uidParam(uid),
                        screenNameParam(screenName)),
                User.class);
    }

    public User destroyFriendship(Uid uid) throws WeiboClientException {
        return destroyFriendship(uid, ScreenName.EMPTY);
    }

    public User destroyFriendship(ScreenName screenName) throws WeiboClientException {
        return destroyFriendship(Uid.EMPTY, screenName);
    }

    public User destroyFriendship(Uid uid, ScreenName screenName) throws WeiboClientException {
        return doPost("friendships/destroy",
                withParams(
                        uidParam(uid),
                        screenNameParam(screenName)),
                User.class);
    }

    public User updateRemark(Uid uid, Remark remark) throws WeiboClientException {
        return doPost("friendships/remark/update",
                withParams(
                        uidParam(uid),
                        remarkParam(remark)),
                User.class);
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
        return doGet("favorites/show",
                withParams(
                        idParam(id)),
                FavoritesItem.class);
    }

    public Favorites getFavoritesByTags(Tid tid) throws WeiboClientException {
        return getFavoritesByTags(tid, Paging.EMPTY);
    }

    public Favorites getFavoritesByTags(Tid tid, Paging paging) throws WeiboClientException {
        return doGet("favorites/by_tags",
                paging,
                withParams(
                        tidParam(tid)),
                Favorites.class);
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
        return doGet("favorites/by_tags/ids",
                paging,
                withParams(
                        tidParam(tid)),
                FavoritesIds.class);
    }

    public FavoritesItem createFavorites(Id id) throws WeiboClientException {
        return doPost("favorites/create",
                withParams(
                        idParam(id)),
                FavoritesItem.class);
    }

    public FavoritesItem destroyFavorites(Id id) throws WeiboClientException {
        return doPost("favorites/destroy",
                withParams(
                        idParam(id)),
                FavoritesItem.class);
    }

    public boolean destroyFavoritesBatch(Collection<Id> ids) throws WeiboClientException {
        ResultResponse response = doPost("favorites/destroy_batch",
                withParams(
                        idsParam(ids)),
                ResultResponse.class);

        return response.isResult();
    }

    public FavoritesItem updateFavoritesTags(Id id) throws WeiboClientException {
        return updateFavoritesTags(id, null);
    }

    public FavoritesItem updateFavoritesTags(Id id, Collection<TagName> tags) throws WeiboClientException {
        return doPost("favorites/tags/update",
                withParams(
                        idParam(id),
                        tagsParam(tags)),
                FavoritesItem.class);
    }

    public Tag updateFavoritesTagsBatch(Tid tid, TagName tagName) throws WeiboClientException {
        return doPost("favorites/tags/update_batch",
                withParams(
                        tidParam(tid),
                        tagParam(tagName)),
                Tag.class);
    }

    public boolean destroyFavoritesTagsBatch(Tid tid) throws WeiboClientException {
        ResultResponse response = doPost("favorites/tags/destroy_batch",
                withParams(
                        tidParam(tid)),
                ResultResponse.class);
        return response.isResult();
    }

    public List<Url> shortenUrl(Collection<UrlLong> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/shorten",
                withParams(
                        urlLongParam(urlList)),
                UrlResponse.class);

        return response.getUrls();
    }

    public List<Url> expandUrl(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/expand",
                withParams(
                        urlShortParam(urlList)),
                UrlResponse.class);

        return response.getUrls();
    }

    public List<Url> getShortUrlClicks(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/clicks",
                withParams(
                        urlShortParam(urlList)),
                UrlResponse.class);

        return response.getUrls();
    }

    public Url getShortUrlReferers(UrlShort urlShort) throws WeiboClientException {
        return doGet("short_url/referers",
                withParams(
                        urlShortParam(urlShort)),
                Url.class);
    }

    public Url getShortUrlLocations(UrlShort urlShort) throws WeiboClientException {
        return doGet("short_url/locations",
                withParams(
                        urlShortParam(urlShort)),
                Url.class);
    }

    public List<Url> getShortUrlShareCounts(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/share/counts",
                withParams(
                        urlShortParam(urlList)),
                UrlResponse.class);

        return response.getUrls();
    }

    public Url getShortUrlShareStatuses(UrlShort urlShort) throws WeiboClientException {
        return getShortUrlShareStatuses(urlShort, Paging.EMPTY);
    }

    public Url getShortUrlShareStatuses(UrlShort urlShort, Paging paging) throws WeiboClientException {
        return doGet("short_url/share/statuses",
                paging,
                withParams(
                        urlShortParam(urlShort)),
                Url.class);
    }

    public List<Url> getShortUrlCommentCounts(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/comment/counts",
                withParams(
                        urlShortParam(urlList)),
                UrlResponse.class);

        return response.getUrls();
    }

    public Url getShortUrlComments(UrlShort urlShort) throws WeiboClientException {
        return getShortUrlComments(urlShort, Paging.EMPTY);
    }

    public Url getShortUrlComments(UrlShort urlShort, Paging paging) throws WeiboClientException {
        return doGet("short_url/comment/comments",
                paging,
                withParams(urlShortParam(urlShort)),
                Url.class);
    }

    public List<UrlInfo> getShortUrlInfo(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlInfoResponse response = doGet("short_url/info",
                withParams(urlShortParam(urlList)),
                UrlInfoResponse.class);

        return response.getUrls();
    }

    public List<Trend> getTrends(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("trends",
                paging,
                withParams(
                        uidParam(uid)),
                WeiboClient.TYPE_TREND_LIST);
    }

    public TrendStatus getTrendStatus(TrendName trendName) throws WeiboClientException {
        return doGet("trends/is_follow",
                withParams(
                        trendNameParam(trendName)),
                TrendStatus.class);
    }

    public GlobalTrendList getTrendsHourly() throws WeiboClientException {
        return getTrendsHourly(BaseApp.No);
    }

    public GlobalTrendList getTrendsHourly(BaseApp baseApp) throws WeiboClientException {
        JsonNode json = doGet("trends/hourly",
                withParams(
                        baseAppParam(baseApp)),
                JsonNode.class);

        return new GlobalTrendList(json);
    }

    public GlobalTrendList getTrendsDaily() throws WeiboClientException {
        return getTrendsDaily(BaseApp.No);
    }

    public GlobalTrendList getTrendsDaily(BaseApp baseApp) throws WeiboClientException {
        JsonNode json = doGet("trends/daily",
                withParams(
                        baseAppParam(baseApp)),
                JsonNode.class);

        return new GlobalTrendList(json);
    }

    public GlobalTrendList getTrendsWeekly() throws WeiboClientException {
        return getTrendsWeekly(BaseApp.No);
    }

    public GlobalTrendList getTrendsWeekly(BaseApp baseApp) throws WeiboClientException {
        JsonNode json = doGet("trends/weekly",
                withParams(
                        baseAppParam(baseApp)),
                JsonNode.class);

        return new GlobalTrendList(json);
    }

    public long followTrend(TrendName trendName) throws WeiboClientException {
        FollowTrendResponse response = doPost("trends/follow",
                withParams(
                        trendNameParam(trendName)),
                FollowTrendResponse.class);

        return response.getTopicid();
    }

    public boolean destroyTrend(TrendId trendId) throws WeiboClientException {
        ResultResponse response = doPost("trends/destroy",
                withParams(
                        trendIdParam(trendId)),
                ResultResponse.class);

        return response.isResult();
    }

    public static Parameters withParams(ParameterAction... actions) {
        Parameters params = Parameters.create();

        for (ParameterAction action : actions) {
            action.addParameter(params);
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

        request.addQuerystringParameter(ACCESS_TOKEN, accessToken.getToken());

        return request;
    }

    public OAuthRequest createPostRequest(String path) {
        OAuthRequest request = new OAuthRequest(Verb.POST, getFullPath(path));
        setRequestTimeout(request);

        request.addBodyParameter(ACCESS_TOKEN, accessToken.getToken());

        return request;
    }

    private void setRequestTimeout(OAuthRequest request) {
        request.setConnectTimeout(connectTimeoutDuration, connectTimeoutUnit);
        request.setReadTimeout(readTimeoutDuration, readTimeoutUnit);
    }

    public String getFullPath(String path) {
        return API2_URL + path + ".json";
    }

    private static class FollowTrendResponse {
        private long topicid;

        public long getTopicid() {
            return topicid;
        }

        public void setTopicid(long topicid) {
            this.topicid = topicid;
        }
    }

    private static class UrlInfoResponse {
        private List<UrlInfo> urls;

        public List<UrlInfo> getUrls() {
            return urls;
        }

        public void setUrls(List<UrlInfo> urls) {
            this.urls = urls;
        }
    }

    private static class UrlResponse {
        private List<Url> urls;

        public List<Url> getUrls() {
            return urls;
        }

        public void setUrls(List<Url> urls) {
            this.urls = urls;
        }
    }

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

    private ParameterAction featureParam(final Feature feature) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (feature != null && feature != Feature.All) {
                    params.add(FEATURE, feature.getValue());
                }
            }
        };
    }

    private ParameterAction trimUserParam(final TrimUser trimUser) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (trimUser == TrimUser.Yes) {
                    params.add(TRIM_USER, trimUser.getValue());
                }
            }
        };
    }

    private ParameterAction screenNameParam(final ScreenName screenName) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (screenName != null && screenName.isValid()) {
                    params.add(SCREEN_NAME, screenName.getValue());
                }
            }
        };
    }

    private ParameterAction uidParam(final Uid uid) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (uid != null && uid.isValid()) {
                    params.add(UID, uid.getValue());
                }
            }
        };
    }

    private ParameterAction baseAppParam(final BaseApp baseApp) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (baseApp == BaseApp.Yes) {
                    params.add(BASE_APP, baseApp.getValue());
                }
            }
        };
    }

    private ParameterAction idParam(final Id id) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (id != null && id.isValid()) {
                    params.add(ID, id.getValue());
                }
            }
        };
    }

    private ParameterAction idListParam(final Collection<Id> idList) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (idList != null && idList.size() > 0) {
                    List<String> idStringList = new ArrayList<String>(idList.size());
                    for (Id id : idList) {
                        idStringList.add(String.valueOf(id.getValue()));
                    }
                    params.add(ID, join(idStringList, ","));
                }
            }
        };
    }

    private ParameterAction midListParam(final Collection<Mid> midList) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (midList != null && midList.size() > 0) {
                    List<String> midStringList = new ArrayList<String>(midList.size());
                    for (Mid mid : midList) {
                        midStringList.add(mid.getValue());
                    }
                    params.add(MID, join(midStringList, ","));
                }
            }
        };
    }

    private ParameterAction filterByAuthorParam(final FilterByAuthor filterByAuthor) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (filterByAuthor != null && filterByAuthor != FilterByAuthor.All) {
                    params.add(FILTER_BY_AUTHOR, filterByAuthor.getValue());
                }
            }
        };
    }

    private ParameterAction filterBySourceParam(final FilterBySource filterBySource) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (filterBySource != null && filterBySource != FilterBySource.All) {
                    params.add(FILTER_BY_SOURCE, filterBySource.getValue());
                }
            }
        };
    }

    private ParameterAction filterByTypeParam(final FilterByType filterByType) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (filterByType != null && filterByType != FilterByType.All) {
                    params.add(FILTER_BY_TYPE, filterByType.getValue());
                }
            }
        };
    }

    private ParameterAction midTypeParam(final MidType midType) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (midType != null && midType != MidType.Status) {
                    params.add(TYPE, midType.getValue());
                }
            }
        };
    }

    private ParameterAction isBatchParam(final IsBatch isBatch) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (isBatch != null && isBatch == IsBatch.Yes) {
                    params.add(IS_BATCH, isBatch.getValue());
                }
            }
        };
    }

    private ParameterAction isBase62Param(final IsBase62 isBase62) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (isBase62 != null && isBase62 == IsBase62.Yes) {
                    params.add(IS_BASE_62, isBase62.getValue());
                }
            }
        };
    }

    private ParameterAction inboxTypeParam(final InboxType inboxType) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (inboxType != null && inboxType == InboxType.Inbox) {
                    params.add(INBOX, inboxType.getValue());
                }
            }
        };
    }

    private ParameterAction midParam(final Mid mid) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (mid != null && mid.isValid()) {
                    params.add(MID, mid.getValue());
                }
            }
        };
    }

    private ParameterAction idsParam(final Collection<Id> ids) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (ids != null && ids.size() > 0) {
                    List<String> idStringList = new ArrayList<String>(ids.size());
                    for (Id id : ids) {
                        idStringList.add(String.valueOf(id.getValue()));
                    }
                    String idsString = join(idStringList, ",");
                    params.add(IDS, idsString);
                }
            }
        };
    }

    public ParameterAction isCommentParam(final IsComment isComment) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (isComment != null && isComment != IsComment.No) {
                    params.add(IS_COMMENT, isComment.getValue());
                }
            }
        };
    }

    private ParameterAction statusParam(final String status) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (isNotBlank(status)) {
                    params.add(STATUS, status);
                }
            }
        };
    }

    private ParameterAction longitudeParam(final Longitude longitude) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (longitude != null) {
                    params.add(LONGITUDE, longitude.getValue());
                }
            }
        };
    }

    private ParameterAction latitudeParam(final Latitude latitude) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (latitude != null) {
                    params.add(LATITUDE, latitude.getValue());
                }
            }
        };
    }

    private ParameterAction urlParam(final URL url) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (url != null) {
                    params.add(URL_, url.toExternalForm());
                }
            }
        };
    }

    private ParameterAction cidsParam(final Collection<Cid> cids) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (cids != null && cids.size() > 0) {
                    List<String> idList = new ArrayList<String>();
                    for (Cid cid : cids) {
                        if (cid.isValid()) {
                            idList.add(String.valueOf(cid.getValue()));
                        }
                    }
                    String idListString = join(idList, ",");
                    params.add(CIDS, idListString);
                }
            }
        };
    }

    public ParameterAction commentOriParam(final CommentOri commentOri) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (commentOri != null && commentOri == CommentOri.Yes) {
                    params.add(COMMENT_ORI, commentOri.getValue());
                }
            }
        };
    }

    private ParameterAction commentParam(final String comment) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (isNotBlank(comment)) {
                    params.add(COMMENT, comment);
                }
            }
        };
    }

    private ParameterAction cidParam(final Cid cid) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (cid != null && cid.isValid()) {
                    params.add(CID, cid.getValue());
                }
            }
        };
    }

    private ParameterAction domainParam(final String domain) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (domain != null) {
                    params.add(DOMAIN, domain);
                }
            }
        };
    }

    private ParameterAction uidsParam(final Collection<Uid> uids) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (uids != null && uids.size() > 0) {
                    List<String> idList = new ArrayList<String>();
                    for (Uid uid : uids) {
                        idList.add(String.valueOf(uid.getValue()));
                    }
                    params.add(UIDS, join(idList, ","));
                }
            }
        };
    }

    private ParameterAction suidParam(final Suid suid) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (suid != null && suid.isValid()) {
                    params.add(SUID, suid.getValue());
                }
            }
        };
    }

    private ParameterAction withoutMentionParam(final WithoutMention withoutMention) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (withoutMention != null && withoutMention == WithoutMention.Yes) {
                    params.add(WITHOUT_MENTION, withoutMention.getValue());
                }
            }
        };
    }

    private ParameterAction targetScreenNameParam(final TargetScreenName targetScreenName) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (targetScreenName != null && targetScreenName.isValid()) {
                    params.add(TARGET_SCREEN_NAME, targetScreenName.getValue());
                }
            }
        };
    }

    private ParameterAction targetUidParam(final TargetUid targetUid) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (targetUid != null && targetUid.isValid()) {
                    params.add(TARGET_ID, targetUid.getValue());
                }
            }
        };
    }

    private ParameterAction sourceScreenNameParam(final SourceScreenName sourceScreenName) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (sourceScreenName != null && sourceScreenName.isValid()) {
                    params.add(SOURCE_SCREEN_NAME, sourceScreenName.getValue());
                }
            }
        };
    }

    private ParameterAction sourceUidParam(final SourceUid sourceUid) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (sourceUid != null && sourceUid.isValid()) {
                    params.add(SOURCE_ID, sourceUid.getValue());
                }
            }
        };
    }

    private ParameterAction remarkParam(final Remark remark) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (remark != null && remark.isValid()) {
                    params.add(REMARK, remark.getValue());
                }
            }
        };
    }

    private ParameterAction tidParam(final Tid tid) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (tid != null && tid.isValid()) {
                    params.add(TID, tid.getValue());
                }
            }
        };
    }

    private ParameterAction tagsParam(final Collection<TagName> tags) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (tags != null && tags.size() > 0) {
                    List<String> tagList = new ArrayList<String>(tags.size());
                    for (TagName tag : tags) {
                        if (tag.isValid()) {
                            tagList.add(tag.getValue());
                        }
                    }
                    params.add(TAGS, join(tagList, ","));
                }
            }
        };
    }

    private ParameterAction tagParam(final TagName tagName) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (tagName != null && tagName.isValid()) {
                    params.add(TAG, tagName.getValue());
                }
            }
        };
    }

    private ParameterAction urlLongParam(final Collection<UrlLong> urlList) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (urlList != null && urlList.size() > 0) {
                    for (UrlLong urlLong : urlList) {
                        if (urlLong.isValid()) {
                            params.add(URL_LONG, urlLong.getValue());
                        }
                    }
                }
            }
        };
    }

    private ParameterAction urlShortParam(final Collection<UrlShort> urlList) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (urlList != null && urlList.size() > 0) {
                    for (UrlShort urlShort : urlList) {
                        if (urlShort.isValid()) {
                            params.add(URL_SHORT, urlShort.getValue());
                        }
                    }
                }
            }
        };
    }

    private ParameterAction urlShortParam(final UrlShort urlShort) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (urlShort != null && urlShort.isValid()) {
                    params.add(URL_SHORT, urlShort.getValue());
                }
            }
        };
    }

    private ParameterAction trendNameParam(final TrendName trendName) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (trendName != null && trendName.isValid()) {
                    params.add(TREND_NAME, trendName.getValue());
                }
            }
        };
    }

    private ParameterAction trendIdParam(final TrendId trendId) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (trendId != null && trendId.isValid()) {
                    params.add(TREND_ID, trendId.getValue());
                }
            }
        };
    }
}
