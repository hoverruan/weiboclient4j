package weiboclient4j;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.scribe.model.OAuthRequest;
import weiboclient4j.model.Count;
import weiboclient4j.model.Emotion;
import weiboclient4j.model.IdResponse;
import weiboclient4j.model.MidResponse;
import weiboclient4j.model.RepostTimeline;
import weiboclient4j.model.Status;
import weiboclient4j.model.Timeline;
import weiboclient4j.model.TimelineIds;
import weiboclient4j.model.UploadedPic;
import weiboclient4j.params.Id;
import weiboclient4j.params.IsBatch;
import weiboclient4j.params.IsComment;
import weiboclient4j.params.Latitude;
import weiboclient4j.params.ListId;
import weiboclient4j.params.Longitude;
import weiboclient4j.params.Mid;
import weiboclient4j.params.MidType;
import weiboclient4j.params.Paging;
import weiboclient4j.params.ParameterAction;
import weiboclient4j.params.Parameters;
import weiboclient4j.params.PicId;
import weiboclient4j.params.ScreenName;
import weiboclient4j.params.StatusParam;
import weiboclient4j.params.Uid;
import weiboclient4j.params.Visible;
import static weiboclient4j.utils.StringUtils.isNotBlank;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Status API
 *
 * @author Hover Ruan
 */
@SuppressWarnings("unchecked")
public class StatusService extends AbstractService {

    private static final String PIC_PARAM_NAME = "pic";

    public StatusService(WeiboClient client) {
        super(client);
    }

    public static interface GetPublicTimelineParam extends ParameterAction {
    }

    public Timeline getPublicTimeline() throws WeiboClientException {
        return getPublicTimeline(Paging.EMPTY);
    }

    public <T extends GetPublicTimelineParam> Timeline getPublicTimeline(T... optionalParams)
            throws WeiboClientException {
        return doGet("statuses/public_timeline", Paging.EMPTY, buildParams(optionalParams), Timeline.class);
    }

    public <T extends GetPublicTimelineParam> Timeline getPublicTimeline(
            Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("statuses/public_timeline", paging, buildParams(optionalParams), Timeline.class);
    }

    public static interface GetFriendsTimelineParam extends ParameterAction {
    }

    public Timeline getFriendsTimeline() throws WeiboClientException {
        return getFriendsTimeline(Paging.EMPTY);
    }

    public <T extends GetFriendsTimelineParam> Timeline getFriendsTimeline(T... optionalParams)
            throws WeiboClientException {
        return doGet("statuses/friends_timeline", Paging.EMPTY, buildParams(optionalParams), Timeline.class);
    }

    public <T extends GetFriendsTimelineParam> Timeline getFriendsTimeline(
            Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("statuses/friends_timeline", paging, buildParams(optionalParams), Timeline.class);
    }

    public static interface GetHomeTimelineParam extends ParameterAction {
    }

    public Timeline getHomeTimeline() throws WeiboClientException {
        return getHomeTimeline(Paging.EMPTY);
    }

    public <T extends GetHomeTimelineParam> Timeline getHomeTimeline(T... optionalParams)
            throws WeiboClientException {
        return doGet("statuses/home_timeline", Paging.EMPTY, buildParams(optionalParams), Timeline.class);
    }

    public <T extends GetHomeTimelineParam> Timeline getHomeTimeline(
            Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("statuses/home_timeline", paging, buildParams(optionalParams), Timeline.class);
    }

    public static interface GetFriendsTimelineIdsParam extends ParameterAction {
    }

    public TimelineIds getFriendsTimelineIds() throws WeiboClientException {
        return getFriendsTimelineIds(Paging.EMPTY);
    }

    public <T extends GetFriendsTimelineIdsParam> TimelineIds getFriendsTimelineIds(T... optionalParams)
            throws WeiboClientException {
        return doGet("statuses/friends_timeline/ids", Paging.EMPTY, buildParams(optionalParams), TimelineIds.class);
    }

    public <T extends GetFriendsTimelineIdsParam> TimelineIds getFriendsTimelineIds(
            Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("statuses/friends_timeline/ids", paging, buildParams(optionalParams), TimelineIds.class);
    }

    public static interface GetUserTimelineParam extends ParameterAction {
    }

    public <T extends GetUserTimelineParam> Timeline getUserTimeline(T... optionalParams)
            throws WeiboClientException {
        return getUserTimeline(Uid.EMPTY, ScreenName.EMPTY, Paging.EMPTY, optionalParams);
    }

    public <T extends GetUserTimelineParam> Timeline getUserTimeline(Paging paging, T... optionalParams)
            throws WeiboClientException {
        return getUserTimeline(Uid.EMPTY, ScreenName.EMPTY, paging, optionalParams);
    }

    public Timeline getUserTimeline(Uid uid) throws WeiboClientException {
        return getUserTimeline(uid, Paging.EMPTY);
    }

    public <T extends GetUserTimelineParam> Timeline getUserTimeline(
            Uid uid, T... optionalParams) throws WeiboClientException {
        return getUserTimeline(uid, ScreenName.EMPTY, Paging.EMPTY, optionalParams);
    }

    public <T extends GetUserTimelineParam> Timeline getUserTimeline(
            Uid uid, Paging paging, T... optionalParams) throws WeiboClientException {
        return getUserTimeline(uid, ScreenName.EMPTY, paging, optionalParams);
    }

    public Timeline getUserTimeline(ScreenName screenName) throws WeiboClientException {
        return getUserTimeline(screenName, Paging.EMPTY);
    }

    public <T extends GetUserTimelineParam> Timeline getUserTimeline(
            ScreenName screenName, Paging paging, T... optionalParams) throws WeiboClientException {
        return getUserTimeline(Uid.EMPTY, screenName, paging, optionalParams);
    }

    private <T extends GetUserTimelineParam> Timeline getUserTimeline(
            Uid uid, ScreenName screenName, Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("statuses/user_timeline", paging,
                buildParams(optionalParams, uid, screenName), Timeline.class);
    }

    public static interface GetUserTimelineIdsParam extends ParameterAction {
    }

    public <T extends GetUserTimelineIdsParam> TimelineIds getUserTimelineIds(
            T... optionalParams) throws WeiboClientException {
        return getUserTimelineIds(Uid.EMPTY, ScreenName.EMPTY, Paging.EMPTY, optionalParams);
    }

    public <T extends GetUserTimelineIdsParam> TimelineIds getUserTimelineIds(
            Paging paging, T... optionalParams) throws WeiboClientException {
        return getUserTimelineIds(Uid.EMPTY, ScreenName.EMPTY, paging, optionalParams);
    }

    public TimelineIds getUserTimelineIds(ScreenName screenName) throws WeiboClientException {
        return getUserTimelineIds(screenName, Paging.EMPTY);
    }

    public <T extends GetUserTimelineIdsParam> TimelineIds getUserTimelineIds(
            ScreenName screenName, T... optionalParams) throws WeiboClientException {
        return getUserTimelineIds(screenName, Paging.EMPTY, optionalParams);
    }

    public <T extends GetUserTimelineIdsParam> TimelineIds getUserTimelineIds(
            ScreenName screenName, Paging paging, T... optionalParams) throws WeiboClientException {
        return getUserTimelineIds(Uid.EMPTY, screenName, paging, optionalParams);
    }

    public TimelineIds getUserTimelineIds(Uid uid) throws WeiboClientException {
        return getUserTimelineIds(uid, Paging.EMPTY);
    }

    public <T extends GetUserTimelineIdsParam> TimelineIds getUserTimelineIds(
            Uid uid, T... optionalParams) throws WeiboClientException {
        return getUserTimelineIds(uid, Paging.EMPTY, optionalParams);
    }

    public <T extends GetUserTimelineIdsParam> TimelineIds getUserTimelineIds(
            Uid uid, Paging paging, T... optionalParams) throws WeiboClientException {
        return getUserTimelineIds(uid, ScreenName.EMPTY, paging, optionalParams);
    }

    private <T extends GetUserTimelineIdsParam> TimelineIds getUserTimelineIds(
            Uid uid, ScreenName screenName, Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("statuses/user_timeline/ids", paging, buildParams(optionalParams, uid, screenName),
                TimelineIds.class);
    }

    public static interface GetRepostTimelineParam extends ParameterAction {
    }

    public RepostTimeline getRepostTimeline(Id id) throws WeiboClientException {
        return getRepostTimeline(id, Paging.EMPTY);
    }

    public <T extends GetRepostTimelineParam> RepostTimeline getRepostTimeline(
            Id id, T... optionalParams) throws WeiboClientException {
        return doGet("statuses/repost_timeline", Paging.EMPTY, buildParams(optionalParams, id), RepostTimeline.class);
    }

    public <T extends GetRepostTimelineParam> RepostTimeline getRepostTimeline(
            Id id, Paging paging, T... optionalParams) throws WeiboClientException {
        try {
            return doGet("statuses/repost_timeline", paging, buildParams(optionalParams, id), RepostTimeline.class);
        } catch (WeiboClientException e) {
            if (e.getWeiboError() == null && isNotBlank(e.getResponseBody())) {
                if ("[]".equals(e.getResponseBody())) {
                    return RepostTimeline.EMPTY;
                }
            }

            throw e;
        }
    }

    public static interface GetRepostTimelineIdsParam extends ParameterAction {
    }

    public TimelineIds getRepostTimelineIds(Id id) throws WeiboClientException {
        return getRepostTimelineIds(id, Paging.EMPTY);
    }

    public <T extends GetRepostTimelineIdsParam> TimelineIds getRepostTimelineIds(
            Id id, T... optionalParams) throws WeiboClientException {
        return doGet("statuses/repost_timeline/ids", Paging.EMPTY, buildParams(optionalParams, id), TimelineIds.class);
    }

    public <T extends GetRepostTimelineIdsParam> TimelineIds getRepostTimelineIds(
            Id id, Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("statuses/repost_timeline/ids", paging, buildParams(optionalParams, id), TimelineIds.class);
    }

    public RepostTimeline getRepostByMe() throws WeiboClientException {
        return getRepostByMe(Paging.EMPTY);
    }

    public RepostTimeline getRepostByMe(Paging paging) throws WeiboClientException {
        return doGet("statuses/repost_by_me", paging, RepostTimeline.class);
    }

    public static interface GetMentionsParam extends ParameterAction {
    }

    public <T extends GetMentionsParam> Timeline getMentions(T... optionalParams) throws WeiboClientException {
        return getMentions(Paging.EMPTY, optionalParams);
    }

    public <T extends GetMentionsParam> Timeline getMentions(Paging paging, T... optionalParams)
            throws WeiboClientException {
        return doGet("statuses/mentions", paging, buildParams(optionalParams), Timeline.class);
    }

    public static interface GetMentionsIdsParam extends ParameterAction {
    }

    public <T extends GetMentionsIdsParam> TimelineIds getMentionsIds(T... optionalParams)
            throws WeiboClientException {
        return doGet("statuses/mentions/ids", Paging.EMPTY, buildParams(optionalParams), TimelineIds.class);
    }

    public <T extends GetMentionsIdsParam> TimelineIds getMentionsIds(
            Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("statuses/mentions/ids", paging, buildParams(optionalParams), TimelineIds.class);
    }

    public static interface GetBilateralTimelineParam extends ParameterAction {
    }

    public <T extends GetBilateralTimelineParam> Timeline getBilateralTimeline(T... optionalParams)
            throws WeiboClientException {
        return doGet("statuses/bilateral_timeline", Paging.EMPTY, buildParams(optionalParams), Timeline.class);
    }

    public <T extends GetBilateralTimelineParam> Timeline getBilateralTimeline(
            Paging paging, T... optionalParams) throws WeiboClientException {
        return doGet("statuses/bilateral_timeline", paging, buildParams(optionalParams), Timeline.class);
    }

    public Status show(Id id) throws WeiboClientException {
        return doGet("statuses/show", withParams(id), Status.class);
    }

    public String queryMid(Id id, MidType midType) throws WeiboClientException {
        MidResponse midResponse = doGet("statuses/querymid",
                withParams(id, midType), MidResponse.class);

        return midResponse != null ? midResponse.getMid() : null;
    }

    public Map<Long, String> queryMidList(Collection<Id> idList, MidType midType) throws WeiboClientException {
        // [{"3436240135184587":"yfcLPlKKn"},{"3436255091659029":"yfd9X6XAx"}]
        ArrayNode arrayNode = doGet("statuses/querymid",
                withParams(Id.idParam(idList), midType, IsBatch.Yes), ArrayNode.class);

        Map<Long, String> map = new HashMap<Long, String>();
        for (int i = 0; i < arrayNode.size(); i++) {
            JsonNode node = arrayNode.get(i);
            Iterator<String> fieldNames = node.getFieldNames();
            while (fieldNames.hasNext()) {
                String idString = fieldNames.next();
                map.put(new Long(idString), node.get(idString).asText());
            }
        }

        return map;
    }

    public static interface QueryIdParam extends ParameterAction {
    }

    public <T extends QueryIdParam> long queryId(
            Mid mid, MidType type, T... optionalParams) throws WeiboClientException {
        IdResponse idResponse = doGet("statuses/queryid", buildParams(optionalParams, mid, type), IdResponse.class);

        return idResponse.getId();
    }

    public static interface QueryIdListParam extends ParameterAction {
    }

    public <T extends QueryIdListParam> Map<String, Long> queryIdList(
            Collection<Mid> midList, MidType type, T... optionalParams)
            throws WeiboClientException {
        // [{"yfcLPlKKn":"3436240135184587"},{"yfd9X6XAx":"3436255091659029"}]
        ArrayNode arrayNode = doGet("statuses/queryid", buildParams(optionalParams, Mid.midParam(midList), type,
                IsBatch.Yes), ArrayNode.class);

        Map<String, Long> map = new HashMap<String, Long>();
        for (int i = 0; i < arrayNode.size(); i++) {
            JsonNode node = arrayNode.get(i);
            Iterator<String> fieldNames = node.getFieldNames();
            while (fieldNames.hasNext()) {
                String mid = fieldNames.next();
                map.put(mid, node.get(mid).asLong());
            }
        }

        return map;
    }

    public List<Count> getStatusesCounts(Collection<Id> ids) throws WeiboClientException {
        return doGet("statuses/count",
                withParams(Id.idsParam(ids)), Count.TYPE_COUNT_LIST);
    }

    public Status repost(Id id, String status) throws WeiboClientException {
        return repost(id, status, IsComment.No);
    }

    public Status repost(Id id, String status, IsComment isComment) throws WeiboClientException {
        return repost(id, new StatusParam(status), isComment);
    }

    public Status repost(Id id, StatusParam status, IsComment isComment) throws WeiboClientException {
        return doPost("statuses/repost", withParams(id, status, isComment), Status.class);
    }

    public Status update(String status) throws WeiboClientException {
        return update(status, null, null);
    }

    public Status update(String status, Latitude latitude, Longitude longitude) throws WeiboClientException {
        return update(new StatusParam(status), latitude, longitude);
    }

    public Status update(StatusParam status, Latitude latitude, Longitude longitude) throws WeiboClientException {
        return doPost("statuses/update", withParams(status, latitude, longitude), Status.class);
    }

    public Status destroy(Id id) throws WeiboClientException {
        return doPost("statuses/destroy", withParams(id), Status.class);
    }

    public static interface UploadImageUrlParam extends ParameterAction {
    }

    public Status uploadImageUrl(String status, URL url, UploadImageUrlParam... optionalParams)
        throws WeiboClientException {
        return uploadImageUrl(new StatusParam(status), url, optionalParams);
    }

    public Status uploadImageUrl(StatusParam status, URL url, UploadImageUrlParam... optionalParams)
            throws WeiboClientException {
        return uploadImageUrl(status, url, null, optionalParams);
    }

    public Status uploadImageUrl(String status, PicId picId, UploadImageUrlParam... optionalParams)
            throws WeiboClientException {
        return uploadImageUrl(new StatusParam(status), picId, optionalParams);
    }

    public Status uploadImageUrl(StatusParam status, PicId picId, UploadImageUrlParam... optionalParams)
            throws WeiboClientException {
        return uploadImageUrl(status, null, picId, optionalParams);
    }

    public Status uploadImageUrl(String status, URL url, PicId picId, UploadImageUrlParam... optionalParams)
            throws WeiboClientException {
        return uploadImageUrl(new StatusParam(status), url, picId, optionalParams);
    }

    public Status uploadImageUrl(StatusParam status, URL url, PicId picId, UploadImageUrlParam... optionalParams)
            throws WeiboClientException {
        return doPost("statuses/upload_url_text", buildParams(optionalParams, status, urlParam(url), picId),
                Status.class);
    }

    public Status uploadImageBinary(StatusParam status, File image) throws WeiboClientException {
        return uploadImageBinary(status, Visible.All, null, image, null, null);
    }

    public Status uploadImageBinary(StatusParam status, Visible visible, ListId listId, File image)
            throws WeiboClientException {
        return uploadImageBinary(status, visible, listId, image, null, null);
    }

    public Status uploadImageBinary(StatusParam status, File image, Latitude latitude, Longitude longitude)
            throws WeiboClientException {
        return uploadImageBinary(status, null, null, image, latitude, longitude);
    }

    public Status uploadImageBinary(StatusParam status, Visible visible, ListId listId, File image, Latitude latitude,
                                    Longitude longitude) throws WeiboClientException {
        OAuthRequest request = createPostRequest("statuses/upload");
        Parameters params = withParams(status, visible, listId, latitude, longitude);

        try {
            buildUploadRequest(request, PIC_PARAM_NAME, image, params);
        } catch (IOException e) {
            throw new WeiboClientException(e);
        }

        return sendRequestAndGetResponseObject(request, Status.class);
    }

    public List<Emotion> getEmotions() throws WeiboClientException {
        return doGet("emotions", Emotion.TYPE_EMOTION_LIST);
    }

    public UploadedPic uploadImage(File image) throws WeiboClientException {
        OAuthRequest request = createPostRequest("statuses/upload_pic");

        try {
            buildUploadRequest(request, PIC_PARAM_NAME, image, Parameters.create());
        } catch (IOException e) {
            throw new WeiboClientException(e);
        }

        return sendRequestAndGetResponseObject(request, UploadedPic.class);
    }
}
