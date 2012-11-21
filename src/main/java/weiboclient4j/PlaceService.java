package weiboclient4j;

import weiboclient4j.model.PoiList;
import weiboclient4j.model.Status;
import weiboclient4j.model.Timeline;
import weiboclient4j.model.UserLBS;
import weiboclient4j.params.BaseApp;
import weiboclient4j.params.EndTime;
import weiboclient4j.params.FriendshipType;
import weiboclient4j.params.Id;
import weiboclient4j.params.Latitude;
import weiboclient4j.params.LocRange;
import weiboclient4j.params.LocSortType;
import weiboclient4j.params.Longitude;
import weiboclient4j.params.Paging;
import weiboclient4j.params.PoiId;
import weiboclient4j.params.StartTime;
import weiboclient4j.params.Uid;

/**
 * @author Hover Ruan
 */
public class PlaceService extends AbstractService {
    public PlaceService(WeiboClient2 client) {
        super(client);
    }

    public Timeline getPublishTimeline() throws WeiboClientException {
        return getPublishTimeline(Paging.EMPTY);
    }

    public Timeline getPublishTimeline(Paging paging) throws WeiboClientException {
        return getPublishTimeline(BaseApp.No, paging);
    }

    public Timeline getPublishTimeline(BaseApp baseApp) throws WeiboClientException {
        return getPublishTimeline(baseApp, Paging.EMPTY);
    }

    public Timeline getPublishTimeline(BaseApp baseApp, Paging paging) throws WeiboClientException {
        return doGet("place/public_timeline", paging, withParams(baseApp), Timeline.class);
    }

    public Timeline getFriendsTimeline() throws WeiboClientException {
        return getFriendsTimeline(Paging.EMPTY);
    }

    public Timeline getFriendsTimeline(Paging paging) throws WeiboClientException {
        return getFriendsTimeline(FriendshipType.Followed, paging);
    }

    public Timeline getFriendsTimeline(FriendshipType type) throws WeiboClientException {
        return getFriendsTimeline(type, Paging.EMPTY);
    }

    public Timeline getFriendsTimeline(FriendshipType type, Paging paging) throws WeiboClientException {
        return doGet("place/friends_timeline", paging, withParams(type), Timeline.class);
    }

    public Timeline getUserTimeline(Uid uid) throws WeiboClientException {
        return getUserTimeline(uid, Paging.EMPTY);
    }

    public Timeline getUserTimeline(Uid uid, BaseApp baseApp) throws WeiboClientException {
        return getUserTimeline(uid, baseApp, Paging.EMPTY);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging) throws WeiboClientException {
        return getUserTimeline(uid, BaseApp.No, paging);
    }

    public Timeline getUserTimeline(Uid uid, BaseApp baseApp, Paging paging) throws WeiboClientException {
        return doGet("place/user_timeline", paging, withParams(uid, baseApp), Timeline.class);
    }

    public Timeline getPoiTimeline(PoiId poiId) throws WeiboClientException {
        return getPoiTimeline(poiId, Paging.EMPTY);
    }

    public Timeline getPoiTimeline(PoiId poiId, BaseApp baseApp) throws WeiboClientException {
        return getPoiTimeline(poiId, baseApp, Paging.EMPTY);
    }

    public Timeline getPoiTimeline(PoiId poiId, Paging paging) throws WeiboClientException {
        return getPoiTimeline(poiId, BaseApp.No, paging);
    }

    public Timeline getPoiTimeline(PoiId poiId, BaseApp baseApp, Paging paging) throws WeiboClientException {
        return doGet("place/poi_timeline", paging, withParams(poiId, baseApp), Timeline.class);
    }

    public Timeline getNearbyTimeline(Latitude latitude, Longitude longitude) throws WeiboClientException {
        return getNearbyTimeline(latitude, longitude, Paging.EMPTY);
    }

    public Timeline getNearbyTimeline(Latitude latitude, Longitude longitude, Paging paging) throws WeiboClientException {
        return getNearbyTimeline(latitude, longitude, BaseApp.No, paging);
    }

    public Timeline getNearbyTimeline(Latitude latitude, Longitude longitude, BaseApp baseApp) throws WeiboClientException {
        return getNearbyTimeline(latitude, longitude, baseApp, Paging.EMPTY);
    }

    public Timeline getNearbyTimeline(Latitude latitude, Longitude longitude, BaseApp baseApp, Paging paging) throws WeiboClientException {
        return getNearbyTimeline(latitude, longitude, null, null, null, null, baseApp, null, paging);
    }

    public Timeline getNearbyTimeline(Latitude latitude, Longitude longitude, LocRange range, StartTime startTime,
                                      EndTime endTime, LocSortType sortType, BaseApp baseApp, LocOffset offset, Paging paging)
            throws WeiboClientException {
        return doGet("place/nearby_timeline", paging, withParams(latitude, longitude, range, startTime,
                endTime, sortType, baseApp, offset), Timeline.class);
    }

    public Status showStatus(Id id) throws WeiboClientException {
        return doGet("place/statuses/show", withParams(id), Status.class);
    }

    public UserLBS showUser(Uid uid) throws WeiboClientException {
        return showUser(uid, BaseApp.No);
    }

    public UserLBS showUser(Uid uid, BaseApp baseApp) throws WeiboClientException {
        return doGet("place/users/show", withParams(uid, baseApp), UserLBS.class);
    }

    public PoiList getUserCheckins(Uid uid) throws WeiboClientException {
        return getUserCheckins(uid, Paging.EMPTY);
    }

    public PoiList getUserCheckins(Uid uid, BaseApp baseApp) throws WeiboClientException {
        return getUserCheckins(uid, baseApp, Paging.EMPTY);
    }

    public PoiList getUserCheckins(Uid uid, Paging paging) throws WeiboClientException {
        return getUserCheckins(uid, BaseApp.No, paging);
    }

    public PoiList getUserCheckins(Uid uid, BaseApp baseApp, Paging paging) throws WeiboClientException {
        return doGet("place/users/checkins", paging,
                withParams(uid, baseApp), PoiList.class);
    }
}
