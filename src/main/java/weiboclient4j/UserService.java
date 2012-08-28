package weiboclient4j;

import weiboclient4j.model.User;
import weiboclient4j.model.UserCount;
import weiboclient4j.params.Domain;
import weiboclient4j.params.ScreenName;
import weiboclient4j.params.Uid;

import java.util.Collection;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class UserService extends AbstractService {

    public UserService(WeiboClient2 client) {
        super(client);
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
}
