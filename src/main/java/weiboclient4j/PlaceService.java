package weiboclient4j;

import org.scribe.model.OAuthRequest;
import weiboclient4j.model.CreatePoiResponse;
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
import weiboclient4j.params.Id;
import weiboclient4j.params.IsPublic;
import weiboclient4j.params.Keyword;
import weiboclient4j.params.Latitude;
import weiboclient4j.params.Longitude;
import weiboclient4j.params.Paging;
import weiboclient4j.params.ParameterAction;
import weiboclient4j.params.Parameters;
import weiboclient4j.params.PoiId;
import weiboclient4j.params.StatusParam;
import weiboclient4j.params.Title;
import weiboclient4j.params.Uid;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Hover Ruan
 */
@SuppressWarnings("unchecked")
public class PlaceService extends AbstractService {

    private static final String PIC_PARAM_NAME = "pic";

    public PlaceService(WeiboClient client) {
        super(client);
    }

    public static interface GetPublishTimelineParam extends ParameterAction {
    }

    public Timeline getPublishTimeline() throws WeiboClientException {
        return getPublishTimeline(Paging.EMPTY);
    }

    public <T extends GetPublishTimelineParam> Timeline getPublishTimeline(T... optionalParams)
            throws WeiboClientException {
        return doGet("place/public_timeline", Paging.EMPTY, buildParams(optionalParams), Timeline.class);
    }

    public <T extends GetPublishTimelineParam> Timeline getPublishTimeline(
            Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("place/public_timeline", paging, buildParams(optionalParams), Timeline.class);
    }

    public static interface GetFriendsTimelineParam extends ParameterAction {
    }

    public Timeline getFriendsTimeline() throws WeiboClientException {
        return getFriendsTimeline(Paging.EMPTY);
    }

    public <T extends GetFriendsTimelineParam> Timeline getFriendsTimeline(T... optionalParams)
            throws WeiboClientException {
        return doGet("place/friends_timeline", Paging.EMPTY, buildParams(optionalParams), Timeline.class);
    }

    public <T extends GetFriendsTimelineParam> Timeline getFriendsTimeline(
            Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("place/friends_timeline", paging, buildParams(optionalParams), Timeline.class);
    }

    public static interface GetUserTimelineParam extends ParameterAction {
    }

    public Timeline getUserTimeline(Uid uid) throws WeiboClientException {
        return getUserTimeline(uid, Paging.EMPTY);
    }

    public <T extends GetUserTimelineParam> Timeline getUserTimeline(Uid uid, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/user_timeline", Paging.EMPTY, buildParams(optionalParams, uid), Timeline.class);
    }

    public <T extends GetUserTimelineParam> Timeline getUserTimeline(
            Uid uid, Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("place/user_timeline", paging, buildParams(optionalParams, uid), Timeline.class);
    }

    public static interface GetPoiTimelineParam extends ParameterAction {
    }

    public Timeline getPoiTimeline(PoiId poiId) throws WeiboClientException {
        return getPoiTimeline(poiId, Paging.EMPTY);
    }

    public <T extends GetPoiTimelineParam> Timeline getPoiTimeline(PoiId poiId, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/poi_timeline", Paging.EMPTY, buildParams(optionalParams, poiId), Timeline.class);
    }

    public <T extends GetPoiTimelineParam> Timeline getPoiTimeline(
            PoiId poiId, Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("place/poi_timeline", paging, buildParams(optionalParams, poiId), Timeline.class);
    }

    public static interface GetNearbyTimelineParam extends ParameterAction {
    }

    public Timeline getNearbyTimeline(Latitude latitude, Longitude longitude) throws WeiboClientException {
        return getNearbyTimeline(latitude, longitude, Paging.EMPTY);
    }

    public <T extends GetNearbyTimelineParam> Timeline getNearbyTimeline(
            Latitude latitude, Longitude longitude, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/nearby_timeline", Paging.EMPTY, buildParams(optionalParams, latitude, longitude),
                Timeline.class);
    }

    public <T extends GetNearbyTimelineParam> Timeline getNearbyTimeline(
            Latitude latitude, Longitude longitude, Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/nearby_timeline", paging, buildParams(optionalParams, latitude, longitude), Timeline.class);
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

    public static interface GetUserCheckinsParam extends ParameterAction {
    }

    public PoiList getUserCheckins(Uid uid) throws WeiboClientException {
        return getUserCheckins(uid, Paging.EMPTY);
    }

    public <T extends GetUserCheckinsParam> PoiList getUserCheckins(
            Uid uid, T... optionalParams) throws WeiboClientException {
        return doGet("place/users/checkins", Paging.EMPTY, buildParams(optionalParams, uid), PoiList.class);
    }

    public <T extends GetUserCheckinsParam> PoiList getUserCheckins(
            Uid uid, Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("place/users/checkins", paging, buildParams(optionalParams, uid), PoiList.class);
    }

    public static interface GetUserPhotosParam extends ParameterAction {
    }

    public StatusList getUserPhotos(Uid uid) throws WeiboClientException {
        return getUserPhotos(uid, Paging.EMPTY);
    }

    public <T extends GetUserPhotosParam> StatusList getUserPhotos(
            Uid uid, T... optionalParams) throws WeiboClientException {
        return doGet("place/users/photos", Paging.EMPTY, buildParams(optionalParams, uid), StatusList.class);
    }

    public <T extends GetUserPhotosParam> StatusList getUserPhotos(
            Uid uid, Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("place/users/photos", paging, buildParams(optionalParams, uid), StatusList.class);
    }

    public static interface GetUserTipsParam extends ParameterAction {
    }

    public StatusList getUserTips(Uid uid) throws WeiboClientException {
        return getUserTips(uid, Paging.EMPTY);
    }

    public <T extends GetUserTipsParam> StatusList getUserTips(
            Uid uid, T... optionalParams) throws WeiboClientException {
        return doGet("place/users/tips", Paging.EMPTY, buildParams(optionalParams, uid), StatusList.class);
    }

    public <T extends GetUserTipsParam> StatusList getUserTips(
            Uid uid, Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("place/users/tips", paging, buildParams(optionalParams, uid), StatusList.class);
    }

    public static interface GetUserTodosParam extends ParameterAction {
    }

    public StatusList getUserTodos(Uid uid) throws WeiboClientException {
        return getUserTodos(uid, Paging.EMPTY);
    }

    public <T extends GetUserTodosParam> StatusList getUserTodos(
            Uid uid, T... optionalParams) throws WeiboClientException {
        return doGet("place/users/todos", Paging.EMPTY, buildParams(optionalParams, uid), StatusList.class);
    }

    public <T extends GetUserTodosParam> StatusList getUserTodos(
            Uid uid, Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("place/users/todos", paging, buildParams(optionalParams, uid), StatusList.class);
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

    public <T extends GetPoiUsersParam> UserList getPoiUsers(PoiId poiId, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/pois/users", Paging.EMPTY, buildParams(optionalParams, poiId), UserList.class);
    }

    public <T extends GetPoiUsersParam> UserList getPoiUsers(PoiId poiId, Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/pois/users", paging, buildParams(optionalParams, poiId), UserList.class);
    }

    public static interface GetPoiTipsParam extends ParameterAction {
    }

    public StatusList getPoiTips(PoiId poiId) throws WeiboClientException {
        return getPoiTips(poiId, Paging.EMPTY);
    }

    public <T extends GetPoiTipsParam> StatusList getPoiTips(PoiId poiId, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/pois/tips", Paging.EMPTY, buildParams(optionalParams, poiId), StatusList.class);
    }

    public <T extends GetPoiTipsParam> StatusList getPoiTips(PoiId poiId, Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/pois/tips", paging, buildParams(optionalParams, poiId), StatusList.class);
    }

    public static interface GetPoiPhotosParam extends ParameterAction {
    }

    public StatusList getPoiPhotos(PoiId poiId) throws WeiboClientException {
        return getPoiPhotos(poiId, Paging.EMPTY);
    }

    public <T extends GetPoiPhotosParam> StatusList getPoiPhotos(PoiId poiId, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/pois/photos", Paging.EMPTY, buildParams(optionalParams, poiId), StatusList.class);
    }

    public <T extends GetPoiPhotosParam> StatusList getPoiPhotos(PoiId poiId, Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/pois/photos", paging, buildParams(optionalParams, poiId), StatusList.class);
    }

    public static interface SearchPoisParam extends ParameterAction {
    }

    public PoiList searchPois(Keyword keyword) throws WeiboClientException {
        return searchPois(keyword, Paging.EMPTY);
    }

    public <T extends SearchPoisParam> PoiList searchPois(Keyword keyword, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/pois/search", Paging.EMPTY, buildParams(optionalParams, keyword), PoiList.class);
    }

    public <T extends SearchPoisParam> PoiList searchPois(Keyword keyword, Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/pois/search", paging, buildParams(optionalParams, keyword), PoiList.class);
    }

    public static interface GetPoiCategoriesParam extends ParameterAction {
    }

    public <T extends GetPoiCategoriesParam> List<PoiCategory> getPoiCategories(T... optionalParams)
            throws WeiboClientException {
        return doGet("place/pois/category", buildParams(optionalParams), PoiCategory.POI_CATEGORY_LIST);
    }

    public static interface GetNearbyPoisParam extends ParameterAction {
    }

    public PoiList getNearbyPois(Latitude latitude, Longitude longitude) throws WeiboClientException {
        return getNearbyPois(latitude, longitude, Paging.EMPTY);
    }

    public <T extends GetNearbyPoisParam> PoiList getNearbyPois(
            Latitude latitude, Longitude longitude, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/nearby/pois", Paging.EMPTY, buildParams(optionalParams, latitude, longitude),
                PoiList.class);
    }

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

    public <T extends GetNearbyUsersParam> UserList getNearbyUsers(
            Latitude latitude, Longitude longitude, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/nearby/users", Paging.EMPTY, buildParams(optionalParams, latitude, longitude),
                UserList.class);
    }

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

    public <T extends GetNearbyPhotosParam> StatusList getNearbyPhotos(
            Latitude latitude, Longitude longitude, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/nearby/photos", Paging.EMPTY, buildParams(optionalParams, latitude, longitude),
                StatusList.class);
    }

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

    public <T extends GetNearbyUsersListParam> UserList getNearbyUsersList(
            Latitude latitude, Longitude longitude, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/nearby_users/list", Paging.EMPTY, buildParams(optionalParams, latitude, longitude),
                UserList.class);
    }

    public <T extends GetNearbyUsersListParam> UserList getNearbyUsersList(
            Latitude latitude, Longitude longitude, Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("place/nearby_users/list", paging, buildParams(optionalParams, latitude, longitude),
                UserList.class);
    }

    public static interface CreatePoiParam extends ParameterAction {
    }

    public <T extends CreatePoiParam> CreatePoiResponse createPoi(
            Title title, Category category, Latitude latitude, Longitude longitude, City city, T... optionalParams)
            throws WeiboClientException {
        return doPost("place/pois/create", buildParams(optionalParams, title, category, latitude, longitude, city),
                CreatePoiResponse.class);
    }

    public Status addCheckin(PoiId poiId, StatusParam status) throws WeiboClientException {
        return addCheckin(poiId, status, null, null);
    }

    public Status addCheckin(PoiId poiId, StatusParam status, IsPublic isPublic) throws WeiboClientException {
        return addCheckin(poiId, status, null, isPublic);
    }

    public Status addCheckin(PoiId poiId, StatusParam status, File image) throws WeiboClientException {
        return addCheckin(poiId, status, image, null);
    }

    public Status addCheckin(PoiId poiId, StatusParam status, File image, IsPublic isPublic)
            throws WeiboClientException {
        OAuthRequest request = createPostRequest("place/pois/add_checkin");
        Parameters params = withParams(poiId, status, isPublic);

        if (image != null) {
            try {
                buildUploadRequest(request, PIC_PARAM_NAME, image, params);
            } catch (IOException e) {
                throw new WeiboClientException(e);
            }
        }

        return sendRequestAndGetResponseObject(request, Status.class);
    }

    public Status addPhoto(PoiId poiId, StatusParam status, File image) throws WeiboClientException {
        return addPhoto(poiId, status, image, null);
    }

    public Status addPhoto(PoiId poiId, StatusParam status, File image, IsPublic isPublic)
            throws WeiboClientException {
        OAuthRequest request = createPostRequest("place/pois/add_photo");
        Parameters params = withParams(poiId, status, isPublic);

        if (image != null) {
            try {
                buildUploadRequest(request, PIC_PARAM_NAME, image, params);
            } catch (IOException e) {
                throw new WeiboClientException(e);
            }
        }

        return sendRequestAndGetResponseObject(request, Status.class);
    }

    public Status addTip(PoiId poiId, StatusParam status) throws WeiboClientException {
        return addTip(poiId, status, null);
    }

    public Status addTip(PoiId poiId, StatusParam status, IsPublic isPublic) throws WeiboClientException {
        return doPost("place/pois/add_tip", withParams(poiId, status, isPublic), Status.class);
    }

    public Status addTodo(PoiId poiId, StatusParam status) throws WeiboClientException {
        return addTodo(poiId, status, null);
    }

    public Status addTodo(PoiId poiId, StatusParam status, IsPublic isPublic) throws WeiboClientException {
        return doPost("place/pois/add_todo", withParams(poiId, status, isPublic), Status.class);
    }

    public int createNearbyUser(Latitude latitude, Longitude longitude) throws WeiboClientException {
        StatusResponse response = doPost("place/nearby_users/create",
                withParams(latitude, longitude), StatusResponse.class);

        return response.getStatus();
    }

    public int destroyNearbyUser() throws WeiboClientException {
        StatusResponse response = doPost("place/nearby_users/destroy", StatusResponse.class);

        return response.getStatus();
    }
}
