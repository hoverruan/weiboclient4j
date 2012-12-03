package weiboclient4j;

import weiboclient4j.model.Poi;
import weiboclient4j.model.PoiCategory;
import weiboclient4j.model.PoiList;
import weiboclient4j.model.Status;
import weiboclient4j.model.StatusList;
import weiboclient4j.model.Timeline;
import weiboclient4j.model.UserLBS;
import weiboclient4j.model.UserList;
import weiboclient4j.params.BaseApp;
import weiboclient4j.params.Category;
import weiboclient4j.params.City;
import weiboclient4j.params.EndBirth;
import weiboclient4j.params.EndTime;
import weiboclient4j.params.FriendshipType;
import weiboclient4j.params.GenderType;
import weiboclient4j.params.Id;
import weiboclient4j.params.Keyword;
import weiboclient4j.params.Latitude;
import weiboclient4j.params.LocOffset;
import weiboclient4j.params.LocRange;
import weiboclient4j.params.LocSortType;
import weiboclient4j.params.Longitude;
import weiboclient4j.params.NearbySortType;
import weiboclient4j.params.Paging;
import weiboclient4j.params.ParameterAction;
import weiboclient4j.params.Pid;
import weiboclient4j.params.PoiCategoryFlag;
import weiboclient4j.params.PoiId;
import weiboclient4j.params.PoiSortType;
import weiboclient4j.params.Query;
import weiboclient4j.params.RelationshipFilter;
import weiboclient4j.params.StartBirth;
import weiboclient4j.params.StartTime;
import weiboclient4j.params.Uid;
import weiboclient4j.params.UserLevel;

import java.util.List;

/**
 * @author Hover Ruan
 */
@SuppressWarnings("unchecked")
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
        return doGet("place/users/checkins", paging, withParams(uid, baseApp), PoiList.class);
    }

    public StatusList getUserPhotos(Uid uid) throws WeiboClientException {
        return getUserPhotos(uid, Paging.EMPTY);
    }

    public StatusList getUserPhotos(Uid uid, BaseApp baseApp) throws WeiboClientException {
        return getUserPhotos(uid, baseApp, Paging.EMPTY);
    }

    public StatusList getUserPhotos(Uid uid, Paging paging) throws WeiboClientException {
        return getUserPhotos(uid, BaseApp.No, paging);
    }

    public StatusList getUserPhotos(Uid uid, BaseApp baseApp, Paging paging) throws WeiboClientException {
        return doGet("place/users/photos", paging, withParams(uid, baseApp), StatusList.class);
    }

    public StatusList getUserTips(Uid uid) throws WeiboClientException {
        return getUserTips(uid, Paging.EMPTY);
    }

    public StatusList getUserTips(Uid uid, BaseApp baseApp) throws WeiboClientException {
        return getUserTips(uid, baseApp, Paging.EMPTY);
    }

    public StatusList getUserTips(Uid uid, Paging paging) throws WeiboClientException {
        return getUserTips(uid, BaseApp.No, paging);
    }

    public StatusList getUserTips(Uid uid, BaseApp baseApp, Paging paging) throws WeiboClientException {
        return doGet("place/users/tips", paging, withParams(uid, baseApp), StatusList.class);
    }

    public StatusList getUserTodos(Uid uid) throws WeiboClientException {
        return getUserTodos(uid, Paging.EMPTY);
    }

    public StatusList getUserTodos(Uid uid, BaseApp baseApp) throws WeiboClientException {
        return getUserTodos(uid, baseApp, Paging.EMPTY);
    }

    public StatusList getUserTodos(Uid uid, Paging paging) throws WeiboClientException {
        return getUserTodos(uid, BaseApp.No, paging);
    }

    public StatusList getUserTodos(Uid uid, BaseApp baseApp, Paging paging) throws WeiboClientException {
        return doGet("place/users/todos", paging, withParams(uid, baseApp), StatusList.class);
    }

    public Poi showPoi(PoiId poiId) throws WeiboClientException {
        return showPoi(poiId, BaseApp.No);
    }

    public Poi showPoi(PoiId poiId, BaseApp baseApp) throws WeiboClientException {
        return doGet("place/pois/show", withParams(poiId, baseApp), Poi.class);
    }

    public static interface GetPoiUsersParam extends ParameterAction {
    }

    public UserList getPoiUsers(PoiId poiId) throws WeiboClientException {
        return getPoiUsers(poiId, Paging.EMPTY);
    }

    /**
     * Optional parameter types: {@link BaseApp}
     */
    public <T extends GetPoiUsersParam> UserList getPoiUsers(PoiId poiId, Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/pois/users", paging, buildParams(optionalParams, poiId), UserList.class);
    }

    public static interface GetPoiTipsParam extends ParameterAction {
    }

    public StatusList getPoiTips(PoiId poiId) throws WeiboClientException {
        return getPoiTips(poiId, Paging.EMPTY);
    }

    /**
     * Optional parameter types: {@link BaseApp} and {@link PoiSortType}
     */
    public <T extends GetPoiTipsParam> StatusList getPoiTips(PoiId poiId, Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/pois/tips", paging, buildParams(optionalParams, poiId), StatusList.class);
    }

    public static interface GetPoiPhotosParam extends ParameterAction {
    }

    public StatusList getPoiPhotos(PoiId poiId) throws WeiboClientException {
        return getPoiPhotos(poiId, Paging.EMPTY);
    }

    /**
     * Optional parameter types: {@link BaseApp} and {@link PoiSortType}
     */
    public <T extends GetPoiPhotosParam> StatusList getPoiPhotos(PoiId poiId, Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/pois/photos", paging, buildParams(optionalParams, poiId), StatusList.class);
    }

    public static interface SearchPoisParam extends ParameterAction {
    }

    public PoiList searchPois(Keyword keyword) throws WeiboClientException {
        return searchPois(keyword, Paging.EMPTY);
    }

    /**
     * Optional parameter types: {@link City} and {@link Category}
     */
    public <T extends SearchPoisParam> PoiList searchPois(Keyword keyword, Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/pois/search", paging, buildParams(optionalParams, keyword), PoiList.class);
    }

    public static interface GetPoiCategoriesParam extends ParameterAction {
    }

    /**
     * Optional parameter types: {@link Pid} and {@link PoiCategoryFlag}
     */
    public <T extends GetPoiCategoriesParam> List<PoiCategory> getPoiCategories(T... optionalParams)
            throws WeiboClientException {
        return doGet("place/pois/category", buildParams(optionalParams), PoiCategory.POI_CATEGORY_LIST);
    }

    public static interface GetNearbyPoisParam extends ParameterAction {
    }

    public PoiList getNearbyPois(Latitude latitude, Longitude longitude) throws WeiboClientException {
        return getNearbyPois(latitude, longitude, Paging.EMPTY);
    }

    /**
     * Optional parameter types: {@link LocRange}, {@link Query}, {@link Category}, {@link NearbySortType} and
     * {@link LocOffset}
     */
    public <T extends GetNearbyPoisParam> PoiList getNearbyPois(
            Latitude latitude, Longitude longitude, Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/nearby/pois", paging, buildParams(optionalParams, latitude, longitude), PoiList.class);
    }

    public static interface GetNearbyUsersParam extends ParameterAction {
    }

    public UserList getNearbyUsers(Latitude latitude, Longitude longitude) throws WeiboClientException {
        return getNearbyUsers(latitude, longitude, Paging.EMPTY);
    }

    /**
     * Optional parameter types: {@link LocRange}, {@link StartTime}, {@link EndTime},{@link NearbySortType} and
     * {@link LocOffset}
     */
    public <T extends GetNearbyUsersParam> UserList getNearbyUsers(
            Latitude latitude, Longitude longitude, Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/nearby/users", paging, buildParams(optionalParams, latitude, longitude), UserList.class);
    }

    public static interface GetNearbyPhotosParam extends ParameterAction {
    }

    public StatusList getNearbyPhotos(Latitude latitude, Longitude longitude) throws WeiboClientException {
        return getNearbyPhotos(latitude, longitude, Paging.EMPTY);
    }

    /**
     * Optional parameter types: {@link LocRange}, {@link StartTime}, {@link EndTime}, {@link LocSortType}
     * and {@link LocOffset}
     */
    public <T extends GetNearbyPhotosParam> StatusList getNearbyPhotos(
            Latitude latitude, Longitude longitude, Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/nearby/photos", paging, buildParams(optionalParams, latitude, longitude), StatusList.class);
    }

    public static interface GetNearbyUsersListParam extends ParameterAction {
    }

    public UserList getNearbyUsersList(Latitude latitude, Longitude longitude) throws WeiboClientException {
        return getNearbyUsersList(latitude, longitude, Paging.EMPTY);
    }

    /**
     * Optional parameter types: {@link LocRange}, {@link LocSortType}, {@link RelationshipFilter}, {@link GenderType},
     * {@link UserLevel}, {@link StartBirth}, {@link EndBirth} and {@link LocOffset}
     */
    public <T extends GetNearbyUsersListParam> UserList getNearbyUsersList(
            Latitude latitude, Longitude longitude, Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/nearby_users/list", paging, buildParams(optionalParams, latitude, longitude), UserList.class);
    }
}
