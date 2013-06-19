package weiboclient4j;

import weiboclient4j.model.UnreadCount;
import weiboclient4j.params.CountType;
import weiboclient4j.params.Uid;

/**
 * @author Hover Ruan
 */
public class RemindService extends AbstractService {
    public RemindService(WeiboClient client) {
        super(client);
    }

    public UnreadCount getUnreadCount() throws WeiboClientException {
        AccountService accountService = getClient().getAccountService();

        Uid uid = new Uid(accountService.getUid());
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
}
