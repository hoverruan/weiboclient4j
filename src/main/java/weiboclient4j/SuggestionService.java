package weiboclient4j;

import weiboclient4j.model.RawSuggestionUser;
import weiboclient4j.model.Status;
import weiboclient4j.model.StatusIdList;
import weiboclient4j.model.StatusList;
import weiboclient4j.model.SuggestionUser;
import static weiboclient4j.model.SuggestionUser.convertFromRawSuggestionUserList;
import weiboclient4j.model.User;
import weiboclient4j.model.UserList;
import weiboclient4j.params.Content;
import weiboclient4j.params.IsPic;
import weiboclient4j.params.Num;
import weiboclient4j.params.Paging;
import weiboclient4j.params.Section;
import weiboclient4j.params.SuggestionStatusType;
import weiboclient4j.params.SuggestionUserCategory;
import weiboclient4j.params.Uid;

import java.util.List;

/**
 * @author Hover Ruan
 */
public class SuggestionService extends AbstractService {
    public SuggestionService(WeiboClient client) {
        super(client);
    }

    public List<User> getUsersHot() throws WeiboClientException {
        return getUsersHot(SuggestionUserCategory.Default);
    }

    public List<User> getUsersHot(SuggestionUserCategory category) throws WeiboClientException {
        return doGet("suggestions/users/hot",
                withParams(category), User.TYPE_USER_LIST);
    }

    public List<SuggestionUser> getUsersMayInterested() throws WeiboClientException {
        return getUsersMayInterested(Paging.EMPTY);
    }

    public List<SuggestionUser> getUsersMayInterested(Paging paging) throws WeiboClientException {
        List<RawSuggestionUser> rawSuggestionUsers =
                doGet("suggestions/users/may_interested", paging,
                        RawSuggestionUser.TYPE_SUGGESTION_USER_LIST);

        return convertFromRawSuggestionUserList(rawSuggestionUsers);
    }

    public UserList getUsersByStatus(Content content) throws WeiboClientException {
        return getUsersByStatus(content, Num.EMPTY);
    }

    public UserList getUsersByStatus(Content content, Num num) throws WeiboClientException {
        return doGet("suggestions/users/by_status",
                withParams(content, num), UserList.class);
    }

    public StatusList getStatusesHot(SuggestionStatusType type, IsPic isPic) throws WeiboClientException {
        return getStatusesHot(type, isPic, Paging.EMPTY);
    }

    public StatusList getStatusesHot(SuggestionStatusType type, IsPic isPic, Paging paging)
            throws WeiboClientException {
        return doGet("suggestions/statuses/hot",
                paging, withParams(type, isPic), StatusList.class);
    }

    public StatusList getStatusesReorder(Section section) throws WeiboClientException {
        return getStatusesReorder(section, Paging.EMPTY);
    }

    public StatusList getStatusesReorder(Section section, Paging paging) throws WeiboClientException {
        return doGet("suggestions/statuses/reorder",
                paging, withParams(section), StatusList.class);
    }

    public StatusIdList getStatusIdsReorder(Section section) throws WeiboClientException {
        return getStatusIdsReorder(section, Paging.EMPTY);
    }

    public StatusIdList getStatusIdsReorder(Section section, Paging paging) throws WeiboClientException {
        return doGet("suggestions/statuses/reorder/ids",
                paging, withParams(section), StatusIdList.class);
    }

    public List<Status> getFavoritesHot() throws WeiboClientException {
        return getFavoritesHot(Paging.EMPTY);
    }

    public List<Status> getFavoritesHot(Paging paging) throws WeiboClientException {
        return doGet("suggestions/favorites/hot", paging, Status.TYPE_STATUS_LIST);
    }

    public User setUserNotInterested(Uid uid) throws WeiboClientException {
        return doPost("suggestions/users/not_interested",
                withParams(uid), User.class);
    }
}
