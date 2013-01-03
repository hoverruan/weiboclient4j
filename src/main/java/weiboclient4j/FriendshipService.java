package weiboclient4j;

import weiboclient4j.model.Friendship;
import weiboclient4j.model.User;
import weiboclient4j.model.UserIdList;
import weiboclient4j.model.UserList;
import weiboclient4j.params.Paging;
import weiboclient4j.params.Remark;
import weiboclient4j.params.ScreenName;
import weiboclient4j.params.SourceScreenName;
import weiboclient4j.params.SourceUid;
import weiboclient4j.params.Suid;
import weiboclient4j.params.TargetScreenName;
import weiboclient4j.params.TargetUid;
import weiboclient4j.params.Uid;

/**
 * @author Hover Ruan
 */
public class FriendshipService extends AbstractService {

    public FriendshipService(WeiboClient client) {
        super(client);
    }

    public UserList getFriends(Uid uid) throws WeiboClientException {
        return getFriends(uid, Paging.EMPTY);
    }

    public UserList getFriends(Uid uid, Paging paging) throws WeiboClientException {
        return getFriends(uid, ScreenName.EMPTY, paging);
    }

    public UserList getFriends(ScreenName screenName) throws WeiboClientException {
        return getFriends(screenName, Paging.EMPTY);
    }

    public UserList getFriends(ScreenName screenName, Paging paging) throws WeiboClientException {
        return getFriends(Uid.EMPTY, screenName, paging);
    }

    private UserList getFriends(Uid uid, ScreenName screenName, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends",
                paging, withParams(uid, screenName), UserList.class);
    }

    public UserIdList getFriendsIds(Uid uid) throws WeiboClientException {
        return getFriendsIds(uid, Paging.EMPTY);
    }

    public UserIdList getFriendsIds(Uid uid, Paging paging) throws WeiboClientException {
        return getFriendsIds(uid, ScreenName.EMPTY, paging);
    }

    public UserIdList getFriendsIds(ScreenName screenName) throws WeiboClientException {
        return getFriendsIds(screenName, Paging.EMPTY);
    }

    public UserIdList getFriendsIds(ScreenName screenName, Paging paging) throws WeiboClientException {
        return getFriendsIds(Uid.EMPTY, screenName, paging);
    }

    private UserIdList getFriendsIds(Uid uid, ScreenName screenName, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends/ids",
                paging, withParams(uid, screenName), UserIdList.class);
    }

    public UserList getFriendsInCommon(Uid uid) throws WeiboClientException {
        return getFriendsInCommon(uid, Suid.EMPTY);
    }

    public UserList getFriendsInCommon(Uid uid, Paging paging) throws WeiboClientException {
        return getFriendsInCommon(uid, Suid.EMPTY, paging);
    }

    public UserList getFriendsInCommon(Uid uid, Suid suid) throws WeiboClientException {
        return getFriendsInCommon(uid, suid, Paging.EMPTY);
    }

    public UserList getFriendsInCommon(Uid uid, Suid suid, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends/in_common",
                paging, withParams(uid, suid), UserList.class);
    }

    public UserList getFriendsBilateral(Uid uid) throws WeiboClientException {
        return getFriendsBilateral(uid, Paging.EMPTY);
    }

    public UserList getFriendsBilateral(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends/bilateral",
                paging, withParams(uid), UserList.class);
    }

    public UserIdList getFriendsBilateralIds(Uid uid) throws WeiboClientException {
        return getFriendsBilateralIds(uid, Paging.EMPTY);
    }

    public UserIdList getFriendsBilateralIds(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends/bilateral/ids",
                paging, withParams(uid), UserIdList.class);
    }

    public UserList getFollowers(ScreenName screenName) throws WeiboClientException {
        return getFollowers(screenName, Paging.EMPTY);
    }

    public UserList getFollowers(ScreenName screenName, Paging paging) throws WeiboClientException {
        return getFollowers(Uid.EMPTY, screenName, paging);
    }

    public UserList getFollowers(Uid uid) throws WeiboClientException {
        return getFollowers(uid, Paging.EMPTY);
    }

    public UserList getFollowers(Uid uid, Paging paging) throws WeiboClientException {
        return getFollowers(uid, ScreenName.EMPTY, paging);
    }

    public UserList getFollowers(Uid uid, ScreenName screenName, Paging paging) throws WeiboClientException {
        return doGet("friendships/followers",
                paging, withParams(uid, screenName), UserList.class);
    }

    public UserIdList getFollowersIds(ScreenName screenName) throws WeiboClientException {
        return getFollowersIds(screenName, Paging.EMPTY);
    }

    public UserIdList getFollowersIds(ScreenName screenName, Paging paging) throws WeiboClientException {
        return getFollowersIds(Uid.EMPTY, screenName, paging);
    }

    public UserIdList getFollowersIds(Uid uid) throws WeiboClientException {
        return getFollowersIds(uid, Paging.EMPTY);
    }

    public UserIdList getFollowersIds(Uid uid, Paging paging) throws WeiboClientException {
        return getFollowersIds(uid, ScreenName.EMPTY, paging);
    }

    public UserIdList getFollowersIds(Uid uid, ScreenName screenName, Paging paging) throws WeiboClientException {
        return doGet("friendships/followers/ids",
                paging, withParams(uid, screenName), UserIdList.class);
    }

    public UserList getActiveFollowers(Uid uid) throws WeiboClientException {
        return getActiveFollowers(uid, Paging.EMPTY);
    }

    public UserList getActiveFollowers(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("friendships/followers/active",
                paging, withParams(uid), UserList.class);
    }

    public UserList getChainFollowers(Uid uid) throws WeiboClientException {
        return getChainFollowers(uid, Paging.EMPTY);
    }

    public UserList getChainFollowers(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("friendships/friends_chain/followers",
                paging, withParams(uid), UserList.class);
    }

    public Friendship show(SourceUid sourceUid, TargetUid targetUid) throws WeiboClientException {
        return show(sourceUid, SourceScreenName.EMPTY, targetUid, TargetScreenName.EMPTY);
    }

    public Friendship show(SourceScreenName sourceScreenName, TargetScreenName targetScreenName)
            throws WeiboClientException {
        return show(SourceUid.EMPTY, sourceScreenName, TargetUid.EMPTY, targetScreenName);
    }

    public Friendship show(SourceUid sourceUid, SourceScreenName sourceScreenName,
                           TargetUid targetUid, TargetScreenName targetScreenName) throws WeiboClientException {
        return doGet("friendships/show",
                withParams(sourceUid, sourceScreenName, targetUid, targetScreenName), Friendship.class);
    }

    public User create(Uid uid) throws WeiboClientException {
        return create(uid, ScreenName.EMPTY);
    }

    public User create(ScreenName screenName) throws WeiboClientException {
        return create(Uid.EMPTY, screenName);
    }

    public User create(Uid uid, ScreenName screenName) throws WeiboClientException {
        return doPost("friendships/create",
                withParams(uid, screenName), User.class);
    }

    public User destroy(Uid uid) throws WeiboClientException {
        return destroy(uid, ScreenName.EMPTY);
    }

    public User destroy(ScreenName screenName) throws WeiboClientException {
        return destroy(Uid.EMPTY, screenName);
    }

    public User destroy(Uid uid, ScreenName screenName) throws WeiboClientException {
        return doPost("friendships/destroy",
                withParams(uid, screenName), User.class);
    }

    public User updateRemark(Uid uid, Remark remark) throws WeiboClientException {
        return doPost("friendships/remark/update",
                withParams(uid, remark), User.class);
    }
}
