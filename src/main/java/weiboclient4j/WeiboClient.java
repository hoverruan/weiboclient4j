package weiboclient4j;

import org.codehaus.jackson.type.TypeReference;
import org.scribe.builder.ServiceBuilder;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import weiboclient4j.model.TokenInfo;
import weiboclient4j.oauth2.DisplayType;
import weiboclient4j.oauth2.ForceLogin;
import weiboclient4j.oauth2.GrantType;
import weiboclient4j.oauth2.ResponseType;
import weiboclient4j.oauth2.SinaWeibo2AccessToken;
import weiboclient4j.oauth2.SinaWeibo2Api;
import weiboclient4j.params.Paging;
import weiboclient4j.params.Parameters;
import static weiboclient4j.utils.StringUtils.isNotBlank;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * API V2 client.
 *
 * @author Hover Ruan
 */
public class WeiboClient {

    private static final int DEFAULT_TIMEOUT = 30;

    private String clientId;

    private String clientSecret;

    private SinaWeibo2AccessToken accessToken;

    private int connectTimeoutDuration = DEFAULT_TIMEOUT;

    private TimeUnit connectTimeoutUnit = TimeUnit.SECONDS;

    private int readTimeoutDuration = DEFAULT_TIMEOUT;

    private TimeUnit readTimeoutUnit = TimeUnit.SECONDS;

    private AbstractService defaultService;

    /**
     * Create api client v2.
     *
     * @param clientId     Client ID, or Api Key
     * @param clientSecret Client Secret, or Api Secret
     */
    public WeiboClient(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;

        initDefaultService();
    }

    public WeiboClient(String accessToken) {
        setAccessToken(accessToken);

        initDefaultService();
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

    public String getAuthorizationUrl(String callback) {
        return getAuthorizationUrl(null, callback);
    }

    public String getAuthorizationUrl(String callback, ForceLogin forceLogin) {
        return getAuthorizationUrl(null, callback, forceLogin);
    }

    public String getAuthorizationUrl(String state, String callback) {
        return getAuthorizationUrl(ResponseType.Code, DisplayType.Default, state, callback);
    }

    public String getAuthorizationUrl(String state, String callback, ForceLogin forceLogin) {
        return getAuthorizationUrl(ResponseType.Code, DisplayType.Default, state, callback, forceLogin);
    }

    public String getAuthorizationUrl(ResponseType responseType, DisplayType displayType, String state,
                                      String callback) {
        return getAuthorizationUrl(responseType, displayType, state, callback, ForceLogin.No);
    }

    public String getAuthorizationUrl(ResponseType responseType, DisplayType displayType, String state,
                                      String callback, ForceLogin forceLogin) {
        SinaWeibo2Api api = new SinaWeibo2Api(responseType, displayType, forceLogin);
        api.setState(state);

        OAuthService service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .callback(callback)
                .provider(api)
                .build();

        return service.getAuthorizationUrl(null);
    }

        /**
         * @deprecated Use {@link #getAccessTokenByCode(String, String)} for 'authorization_code' grant type
         */
    @Deprecated
    public SinaWeibo2AccessToken getAccessToken(GrantType grantType, String code, String callback)
            throws WeiboClientException {
        return getAccessTokenByCode(code, callback);
    }

    public SinaWeibo2AccessToken getAccessTokenByCode(String code, String callback) throws WeiboClientException {
        SinaWeibo2Api api = new SinaWeibo2Api(GrantType.AuthorizationCode);

        ServiceBuilder serviceBuilder = new ServiceBuilder();

        if (isNotBlank(callback)) {
            serviceBuilder.callback(callback);
        }

        OAuthService service = serviceBuilder
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .provider(api)
                .build();

        return retrieveAccessToken(service, null, new Verifier(code));
    }

    public SinaWeibo2AccessToken getAccessTokenByCode(String code) throws WeiboClientException {
        return getAccessTokenByCode(code, null);
    }

    public SinaWeibo2AccessToken getAccessTokenByPassword(String username, String password)
            throws WeiboClientException {
        SinaWeibo2Api api = new SinaWeibo2Api(GrantType.Password);

        OAuthService service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .provider(api)
                .build();

        return retrieveAccessToken(service, new Token(username, password), null);
    }

    public void setAccessToken(SinaWeibo2AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public void setAccessToken(String accessToken) {
        setAccessToken(new SinaWeibo2AccessToken(accessToken));
    }

    public TokenInfo getTokenInfo() throws WeiboClientException {
        return post("https://api.weibo.com/oauth2/get_token_info", TokenInfo.class);
    }

    public StatusService getStatusService() {
        return new StatusService(this);
    }

    public CommentService getCommentService() {
        return new CommentService(this);
    }

    public UserService getUserService() {
        return new UserService(this);
    }

    public FriendshipService getFriendshipService() {
        return new FriendshipService(this);
    }

    public AccountService getAccountService() {
        return new AccountService(this);
    }

    public FavoriteService getFavoriteService() {
        return new FavoriteService(this);
    }

    public TrendService getTrendService() {
        return new TrendService(this);
    }

    public TagService getTagService() {
        return new TagService(this);
    }

    public RegisterService getRegisterService() {
        return new RegisterService(this);
    }

    public SearchService getSearchService() {
        return new SearchService(this);
    }

    public SuggestionService getSuggestionService() {
        return new SuggestionService(this);
    }

    public RemindService getRemindService() {
        return new RemindService(this);
    }

    public ShortUrlService getShortUrlService() {
        return new ShortUrlService(this);
    }

    public NotificationService getNotificationService() {
        return new NotificationService(this);
    }

    public CommonService getCommonService() {
        return new CommonService(this);
    }

    public PlaceService getPlaceService() {
        return new PlaceService(this);
    }

    public LocationService getLocationService() {
        return new LocationService(this);
    }

    public <T> T get(String path, Paging paging, Parameters params, Class<T> clazz) throws WeiboClientException {
        return defaultService.doGet(path, paging, params, clazz);
    }

    public <T> T get(String path, Parameters params, Class<T> clazz) throws WeiboClientException {
        return defaultService.doGet(path, params, clazz);
    }

    public <T> T get(String path, Paging paging, Class<T> clazz) throws WeiboClientException {
        return defaultService.doGet(path, paging, clazz);
    }

    public <T> T get(String path, Class<T> clazz) throws WeiboClientException {
        return defaultService.doGet(path, clazz);
    }

    public <T> List<T> get(String path, Paging paging, Parameters params, TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        return defaultService.doGet(path, paging, params, typeReference);
    }

    public <T> List<T> get(String path, Parameters params, TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        return defaultService.doGet(path, params, typeReference);
    }

    public <T> List<T> get(String path, Paging paging, TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        return defaultService.doGet(path, paging, typeReference);
    }

    public <T> List<T> get(String path, TypeReference<List<T>> typeReference) throws WeiboClientException {
        return defaultService.doGet(path, typeReference);
    }

    public <T> T post(String path, Paging paging, Parameters params, Class<T> clazz) throws WeiboClientException {
        return defaultService.doPost(path, paging, params, clazz);
    }

    public <T> T post(String path, Parameters params, Class<T> clazz) throws WeiboClientException {
        return defaultService.doPost(path, params, clazz);
    }

    public <T> T post(String path, Paging paging, Class<T> clazz) throws WeiboClientException {
        return defaultService.doPost(path, paging, clazz);
    }

    public <T> T post(String path, Class<T> clazz) throws WeiboClientException {
        return defaultService.doPost(path, clazz);
    }

    public <T> List<T> post(String path, Paging paging, Parameters params, TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        return defaultService.doPost(path, paging, params, typeReference);
    }

    public <T> List<T> post(String path, Parameters params, TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        return defaultService.doPost(path, params, typeReference);
    }

    public <T> List<T> post(String path, Paging paging, TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        return defaultService.doPost(path, paging, typeReference);
    }

    public <T> List<T> post(String path, TypeReference<List<T>> typeReference) throws WeiboClientException {
        return defaultService.doPost(path, typeReference);
    }

    protected void initService(AbstractService service) {
        service.setAccessToken(accessToken);
        service.setConnectTimeoutDuration(connectTimeoutDuration);
        service.setConnectTimeoutUnit(connectTimeoutUnit);
        service.setReadTimeoutDuration(readTimeoutDuration);
        service.setReadTimeoutUnit(readTimeoutUnit);
    }

    private void initDefaultService() {
        defaultService = new AbstractService(this);
    }

    private SinaWeibo2AccessToken retrieveAccessToken(OAuthService service, Token token, Verifier verifier)
            throws WeiboClientException {
        try {
            accessToken = (SinaWeibo2AccessToken) service.getAccessToken(token, verifier);
        } catch (OAuthException oe) {
            if (oe.getCause() instanceof WeiboClientException) {
                throw (WeiboClientException) oe.getCause();
            }

            throw new WeiboClientException("OAuth error", oe);
        }

        return accessToken;
    }
}
