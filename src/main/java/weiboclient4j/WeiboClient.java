package weiboclient4j;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.SinaWeiboApi;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import weiboclient4j.utils.SinaJsonNamingStrategy;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hover Ruan
 */
public class WeiboClient {
    private static final Logger log = Logger.getLogger("weibo_client");

    private static final String BASE_URL = "http://api.t.sina.com.cn/";

    // parameter names
    private static final String P_ID = "id";
    private static final String P_IDS = "ids";
    private static final String P_BASE_APP = "base_app";
    private static final String P_STATUS_TYPE = "feature";
    private static final String P_WITH_NEW_STATUS = "with_new_status";
    private static final String P_TYPE = "type";
    private static final String P_LANGUAGE = "language";
    private static final String P_USER_ID = "user_id";
    private static final String P_SCREEN_NAME = "screen_name";

    private OAuthService service;
    private Token accessToken;

    private long userId;
    private ObjectMapper mapper = new ObjectMapper();

    {
        mapper.setPropertyNamingStrategy(new SinaJsonNamingStrategy());

        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.getDeserializationConfig().setDateFormat(format);
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

    public WeiboClient(Token accessToken, long userId) {
        setAccessToken(accessToken);
        setUserId(userId);
    }

    /**
     * For testing
     */
    WeiboClient() {
    }

    //=======================================================================
    //  OAuth methods
    //=======================================================================

    private static final Pattern USER_ID_PATTERN = Pattern.compile("user_id=(\\d+)");

    public void setAccessToken(Token accessToken) {
        this.accessToken = accessToken;

        if (accessToken.getRawResponse() != null) {
            Matcher matcher = USER_ID_PATTERN.matcher(accessToken.getRawResponse());
            if (matcher.find()) {
                setUserId(Long.parseLong(matcher.group(1)));
            }
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

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public User showCurrentUser() throws WeiboClientException {
        assert userId > 0;
        return showUser(userId);
    }

    //=======================================================================
    //  Statuses API
    //=======================================================================

    static final TypeReference<List<Status>> TYPE_STATUS_LIST = new TypeReference<List<Status>>() {
    };

    //*****************************************************
    // statuses/public_timeline
    //*****************************************************

    public List<Status> getPublicStatuses() throws WeiboClientException {
        return get("statuses/public_timeline", TYPE_STATUS_LIST, Paging.EMPTY_PAGING, Parameters.create());
    }

    public List<Status> getPublicStatuses(int count) throws WeiboClientException {
        return getPublicStatuses(count, false);
    }

    public List<Status> getPublicStatuses(int count, boolean baseApp) throws WeiboClientException {
        return get("statuses/public_timeline", TYPE_STATUS_LIST, Paging.create().count(count), Parameters.create().add(P_BASE_APP, baseApp));
    }

    //*****************************************************
    // statuses/friends_timeline
    //*****************************************************

    public List<Status> getFriendsStatuses() throws WeiboClientException {
        return getFriendsStatuses(Paging.EMPTY_PAGING);
    }

    public List<Status> getFriendsStatuses(Paging paging) throws WeiboClientException {
        return getFriendsStatuses(paging, false, StatusType.ALL);
    }

    public List<Status> getFriendsStatuses(Paging paging, boolean baseApp, StatusType type) throws WeiboClientException {
        Parameters params = Parameters.create().add(P_BASE_APP, baseApp);

        if (type != null) {
            params.add(P_STATUS_TYPE, type.ordinal());
        }

        return get("statuses/friends_timeline", TYPE_STATUS_LIST, paging, params);
    }

    //*****************************************************
    // statuses/user_timeline
    //*****************************************************

    public List<Status> getUserStatuses(long userId) throws WeiboClientException {
        return getUserStatuses(userId, Paging.EMPTY_PAGING);
    }

    public List<Status> getUserStatuses(long userId, Paging paging) throws WeiboClientException {
        return getUserStatuses(userId, paging, false, StatusType.ALL);
    }

    public List<Status> getUserStatuses(long userId, Paging paging, boolean baseApp, StatusType type) throws WeiboClientException {
        return getUserStatuses(userId, null, paging, baseApp, type);
    }

    public List<Status> getUserStatuses(String screenName) throws WeiboClientException {
        return getUserStatuses(screenName, Paging.EMPTY_PAGING);
    }

    public List<Status> getUserStatuses(String screenName, Paging paging) throws WeiboClientException {
        return getUserStatuses(screenName, paging, false, StatusType.ALL);
    }

    public List<Status> getUserStatuses(String screenName, Paging paging, boolean baseApp, StatusType type) throws WeiboClientException {
        return getUserStatuses(0, screenName, paging, baseApp, type);
    }

    public List<Status> getUserStatuses(long userId, String screenName, Paging paging, boolean baseApp, StatusType type) throws WeiboClientException {
        Parameters params = Parameters.create().add(P_BASE_APP, baseApp);

        if (userId > 0) {
            params.add(P_USER_ID, userId);
        }

        if (screenName != null && screenName.length() > 0) {
            params.add(P_SCREEN_NAME, screenName);
        }

        if (type != null) {
            params.add(P_STATUS_TYPE, type.ordinal());
        }

        return get("statuses/user_timeline", TYPE_STATUS_LIST, paging, params);
    }

    //*****************************************************
    // statuses/mentions
    //*****************************************************

    public List<Status> getMentionsStatuses() throws WeiboClientException {
        return get("statuses/mentions", TYPE_STATUS_LIST, Paging.EMPTY_PAGING);
    }

    public List<Status> getMentionsStatuses(Paging paging) throws WeiboClientException {
        return get("statuses/mentions", TYPE_STATUS_LIST, paging);
    }

    //*****************************************************
    // statuses/comments_timeline
    //*****************************************************

    static final TypeReference<List<Comment>> TYPE_COMMENT_LIST = new TypeReference<List<Comment>>() {
    };

    public List<Comment> getMyComments() throws WeiboClientException {
        return getMyComments(Paging.EMPTY_PAGING);
    }

    public List<Comment> getMyComments(Paging paging) throws WeiboClientException {
        return get("statuses/comments_timeline", TYPE_COMMENT_LIST, paging);
    }

    //*****************************************************
    // statuses/comments_by_me
    //*****************************************************

    public List<Comment> getCommentsByMe() throws WeiboClientException {
        return getCommentsByMe(Paging.EMPTY_PAGING);
    }

    public List<Comment> getCommentsByMe(Paging paging) throws WeiboClientException {
        return get("statuses/comments_by_me", TYPE_COMMENT_LIST, paging);
    }

    //*****************************************************
    // statuses/comments_to_me
    //*****************************************************

    public List<Comment> getCommentsToMe() throws WeiboClientException {
        return getCommentsToMe(Paging.EMPTY_PAGING);
    }

    public List<Comment> getCommentsToMe(Paging paging) throws WeiboClientException {
        return get("statuses/comments_to_me", TYPE_COMMENT_LIST, paging);
    }

    //*****************************************************
    // statuses/comments
    //*****************************************************

    public List<Comment> getComments(long statusId) throws WeiboClientException {
        return getComments(statusId, 0, 0);
    }

    public List<Comment> getComments(long statusId, int count, int page) throws WeiboClientException {
        Paging paging = Paging.create().count(count).page(page);
        Parameters params = Parameters.create().add(P_ID, statusId);

        return get("statuses/comments", TYPE_COMMENT_LIST, paging, params);
    }

    //*****************************************************
    //  statuses/counts
    //*****************************************************

    static final TypeReference<List<Count>> TYPE_COUNT_LIST = new TypeReference<List<Count>>() {
    };

    public List<Count> getCounts(long... ids) throws WeiboClientException {
        Parameters params = Parameters.create().add(P_IDS, ids);

        return get("statuses/counts", TYPE_COUNT_LIST, Paging.EMPTY_PAGING, params);
    }

    //*****************************************************
    //  statuses/repost_timeline
    //*****************************************************

    public List<Status> getRepostStatuses(long statusId) throws WeiboClientException {
        return getRepostStatuses(statusId, Paging.EMPTY_PAGING);
    }

    public List<Status> getRepostStatuses(long statusId, Paging paging) throws WeiboClientException {
        Parameters params = Parameters.create().add(P_ID, statusId);

        return get("statuses/repost_timeline", TYPE_STATUS_LIST, paging, params);
    }

    //*****************************************************
    //  statuses/repost_timeline
    //*****************************************************

    public List<Status> getRepostStatusesByUser(long userId) throws WeiboClientException {
        return getRepostStatusesByUser(userId, Paging.EMPTY_PAGING);
    }

    public List<Status> getRepostStatusesByUser(long userId, Paging paging) throws WeiboClientException {
        Parameters params = Parameters.create().add(P_ID, userId);

        return get("statuses/repost_by_me", TYPE_STATUS_LIST, paging, params);
    }

    //*****************************************************
    //  statuses/unread
    //*****************************************************

    public UnreadCount getUnreadCount() throws WeiboClientException {
        return getUnreadCount(false, 0);
    }

    public UnreadCount getUnreadCount(boolean withNewStatus, long sinceId) throws WeiboClientException {
        Parameters params = Parameters.create().add(P_WITH_NEW_STATUS, withNewStatus);
        Paging paging = Paging.create().sinceId(sinceId);

        return get("statuses/unread", UnreadCount.class, paging, params);
    }

    //*****************************************************
    //  statuses/reset_count
    //*****************************************************

    public Result resetCount(CounterType type) throws WeiboClientException {
        Parameters params = Parameters.create().add(P_TYPE, type.ordinal() + 1);

        return post("statuses/reset_count", Result.class, Paging.EMPTY_PAGING, params);
    }

    //*****************************************************
    //  emotions
    //*****************************************************

    static final TypeReference<List<Emotion>> TYPE_EMOTION_LIST = new TypeReference<List<Emotion>>() {
    };

    public List<Emotion> getEmotions() throws WeiboClientException {
        return getEmotions(EmotionType.FACE, Language.CNNAME);
    }

    public List<Emotion> getEmotions(EmotionType type, Language language) throws WeiboClientException {
        Parameters params = Parameters.create()
                .add(P_TYPE, type.name().toLowerCase())
                .add(P_LANGUAGE, language.name().toLowerCase());

        return get("emotions", TYPE_EMOTION_LIST, Paging.EMPTY_PAGING, params);
    }

    //=======================================================================
    //  Users API
    //=======================================================================

    //*****************************************************
    //  users/show
    //*****************************************************

    public User showUser(long userId) throws WeiboClientException {
        return get("users/show", User.class, Paging.EMPTY_PAGING, Parameters.create().add(P_USER_ID, userId));
    }

    public User showUser(String screenName) throws WeiboClientException {
        return get("users/show", User.class, Paging.EMPTY_PAGING, Parameters.create().add(P_SCREEN_NAME, screenName));
    }

    //=======================================================================
    // Private methods
    //=======================================================================

    private String getContent(Verb verb, String path, Paging paging, Parameters params) {
        OAuthRequest request = new OAuthRequest(verb, BASE_URL + path + ".json");

        params.add(paging);
        Map<String, String> paramMap = params.buildParameters();
        for (String key : paramMap.keySet()) {
            request.addQuerystringParameter(key, paramMap.get(key));
        }

        service.signRequest(accessToken, request);
        Response response = request.send();

        return response.getBody();
    }

    private <T> T get(String path, Class<T> clazz) throws WeiboClientException {
        return get(path, clazz, Paging.EMPTY_PAGING, Parameters.create());
    }

    private <T> T get(String path, Class<T> clazz, Paging paging, Parameters params) throws WeiboClientException {
        String content = getContent(Verb.GET, path, paging, params);

        return parseJsonObject(content, clazz);
    }

    private <T> List<T> get(String path, TypeReference<List<T>> type, Paging paging) throws WeiboClientException {
        return get(path, type, paging, Parameters.create());
    }

    private <T> List<T> get(String path, TypeReference<List<T>> type, Paging paging, Parameters params) throws WeiboClientException {
        String content = getContent(Verb.GET, path, paging, params);

        return parseJsonObject(content, type);
    }

    private <T> T post(String path, Class<T> clazz) throws WeiboClientException {
        return get(path, clazz, Paging.EMPTY_PAGING, Parameters.create());
    }

    private <T> T post(String path, Class<T> clazz, Paging paging, Parameters params) throws WeiboClientException {
        String content = getContent(Verb.POST, path, paging, params);

        return parseJsonObject(content, clazz);
    }

    private <T> List<T> post(String path, TypeReference<List<T>> type, Paging paging) throws WeiboClientException {
        return get(path, type, paging, Parameters.create());
    }

    private <T> List<T> post(String path, TypeReference<List<T>> type, Paging paging, Parameters params) throws WeiboClientException {
        String content = getContent(Verb.POST, path, paging, params);

        return parseJsonObject(content, type);
    }

    <T> T parseJsonObject(String content, Class<T> clazz) throws WeiboClientException {
        try {
            return mapper.readValue(content, clazz);
        } catch (IOException e) {
            log.warning("Parse failed: " + content);

            throw new WeiboClientException(e);
        }
    }

    <T> List<T> parseJsonObject(String content, TypeReference<List<T>> type) throws WeiboClientException {
        try {
            return mapper.readValue(content, type);
        } catch (IOException e) {
            log.warning("Parse failed: " + content);

            throw new WeiboClientException(e);
        }
    }
}
