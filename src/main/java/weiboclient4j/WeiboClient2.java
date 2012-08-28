package weiboclient4j;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import weiboclient4j.oauth2.DisplayType;
import weiboclient4j.oauth2.GrantType;
import weiboclient4j.oauth2.ResponseType;
import weiboclient4j.oauth2.SinaWeibo2AccessToken;
import weiboclient4j.oauth2.SinaWeibo2Api;

import java.util.concurrent.TimeUnit;

/**
 * API V2 client.
 *
 * @author Hover Ruan
 */
public class WeiboClient2 {
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

    protected void initService(AbstractService service) {
        service.setAccessToken(accessToken);
        service.setConnectTimeoutDuration(connectTimeoutDuration);
        service.setConnectTimeoutUnit(connectTimeoutUnit);
        service.setReadTimeoutDuration(readTimeoutDuration);
        service.setReadTimeoutUnit(readTimeoutUnit);
    }
}
