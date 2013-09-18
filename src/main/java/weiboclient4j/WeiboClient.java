package weiboclient4j;

import org.scribe.builder.ServiceBuilder;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import weiboclient4j.oauth2.DisplayType;
import weiboclient4j.oauth2.GrantType;
import weiboclient4j.oauth2.ResponseType;
import weiboclient4j.oauth2.SinaWeibo2AccessToken;
import weiboclient4j.oauth2.SinaWeibo2Api;
import static weiboclient4j.utils.StringUtils.isNotBlank;

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

    /**
     * Create api client v2.
     *
     * @param clientId     Client ID, or Api Key
     * @param clientSecret Client Secret, or Api Secret
     */
    public WeiboClient(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public WeiboClient(String accessToken) {
        setAccessToken(accessToken);
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

    public String getAuthorizationUrl(ResponseType responseType, DisplayType displayType, String state,
                                      String callback) {
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

    private SinaWeibo2AccessToken retrieveAccessToken(OAuthService service, Token token, Verifier verifier)
            throws WeiboClientException {
        try {
            accessToken = (SinaWeibo2AccessToken) service.getAccessToken(token, verifier);
        } catch (OAuthException oe) {
            if (oe.getCause() instanceof WeiboClientException) {
                throw (WeiboClientException) oe.getCause();
            }
        }

        return accessToken;
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

    public LocationService getLocationService() {
        return new LocationService(this);
    }

    protected void initService(AbstractService service) {
        service.setAccessToken(accessToken);
        service.setConnectTimeoutDuration(connectTimeoutDuration);
        service.setConnectTimeoutUnit(connectTimeoutUnit);
        service.setReadTimeoutDuration(readTimeoutDuration);
        service.setReadTimeoutUnit(readTimeoutUnit);
    }
}
