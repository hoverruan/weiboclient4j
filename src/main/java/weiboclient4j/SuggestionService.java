package weiboclient4j;

import weiboclient4j.model.Status;
import weiboclient4j.model.StatusIdList;
import weiboclient4j.model.StatusList;
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
    public SuggestionService(WeiboClient2 client) {
        super(client);
    }

    public List<User> getSuggestionUsersHot() throws WeiboClientException {
        return getSuggestionUsersHot(SuggestionUserCategory.Default);
    }

    public List<User> getSuggestionUsersHot(SuggestionUserCategory category) throws WeiboClientException {
        return doGet("suggestions/users/hot",
                withParams(category), User.TYPE_USER_LIST);
    }

    // TODO: implements getSuggestionUsersMayInterested
//    public ArrayNode getSuggestionUsersMayInterested(Paging paging) throws WeiboClientException {
//        return doGet("suggestions/users/may_interested", paging, ArrayNode.class);
//    }

    public UserList getSuggestionUsersByStatus(Content content) throws WeiboClientException {
        return getSuggestionUsersByStatus(content, Num.EMPTY);
    }

    public UserList getSuggestionUsersByStatus(Content content, Num num) throws WeiboClientException {
        return doGet("suggestions/users/by_status",
                withParams(content, num), UserList.class);
    }

    public StatusList getSuggestionStatusesHot(SuggestionStatusType type, IsPic isPic) throws WeiboClientException {
        return getSuggestionStatusesHot(type, isPic, Paging.EMPTY);
    }

    public StatusList getSuggestionStatusesHot(SuggestionStatusType type, IsPic isPic, Paging paging) throws WeiboClientException {
        return doGet("suggestions/statuses/hot",
                paging, withParams(type, isPic), StatusList.class);
    }

    public StatusList getSuggestionStatusesReorder(Section section) throws WeiboClientException {
        return getSuggestionStatusesReorder(section, Paging.EMPTY);
    }

    public StatusList getSuggestionStatusesReorder(Section section, Paging paging) throws WeiboClientException {
        return doGet("suggestions/statuses/reorder",
                paging, withParams(section), StatusList.class);
    }

    public StatusIdList getSuggestionStatusIdsReorder(Section section) throws WeiboClientException {
        return getSuggestionStatusIdsReorder(section, Paging.EMPTY);
    }

    public StatusIdList getSuggestionStatusIdsReorder(Section section, Paging paging) throws WeiboClientException {
        return doGet("suggestions/statuses/reorder/ids",
                paging, withParams(section), StatusIdList.class);
    }

    public List<Status> getSuggestionFavoritesHot() throws WeiboClientException {
        return getSuggestionFavoritesHot(Paging.EMPTY);
    }

    public List<Status> getSuggestionFavoritesHot(Paging paging) throws WeiboClientException {
        return doGet("suggestions/favorites/hot", paging, Status.TYPE_STATUS_LIST);
    }

    public User setSuggestionUserNotInterested(Uid uid) throws WeiboClientException {
        return doPost("suggestions/users/not_interested",
                withParams(uid), User.class);
    }
}
