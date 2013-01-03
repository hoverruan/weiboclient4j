package weiboclient4j;

import weiboclient4j.model.AccountUid;
import weiboclient4j.model.Privacy;
import weiboclient4j.model.RateLimitStatus;
import weiboclient4j.model.User;

/**
 * @author Hover Ruan
 */
public class AccountService extends AbstractService {
    public AccountService(WeiboClient client) {
        super(client);
    }

    public long getUid() throws WeiboClientException {
        AccountUid accountUid = doGet("account/get_uid", AccountUid.class);

        return accountUid.getUid();
    }

    public Privacy getPrivacy() throws WeiboClientException {
        return doGet("account/get_privacy", Privacy.class);
    }

    public RateLimitStatus getRateLimitStatus() throws WeiboClientException {
        return doGet("account/rate_limit_status", RateLimitStatus.class);
    }

    public User endSession() throws WeiboClientException {
        return doGet("account/end_session", User.class);
    }
}
