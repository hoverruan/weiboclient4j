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

    public UserService(WeiboClient client) {
        super(client);
    }

    public User show(ScreenName screenName) throws WeiboClientException {
        return show(Uid.EMPTY, screenName);
    }

    public User show(Uid uid) throws WeiboClientException {
        return show(uid, ScreenName.EMPTY);
    }

    private User show(Uid uid, ScreenName screenName) throws WeiboClientException {
        return doGet("users/show", withParams(uid, screenName), User.class);
    }

    public User showByDomain(Domain domain) throws WeiboClientException {
        return doGet("users/domain_show", withParams(domain), User.class);
    }

    public User showByDomain(String domain) throws WeiboClientException {
        return showByDomain(new Domain(domain));
    }

    public List<UserCount> getCounts(Collection<Uid> uids) throws WeiboClientException {
        return doGet("users/counts", withParams(Uid.uidsParam(uids)), UserCount.LIST_TYPE);
    }
}
