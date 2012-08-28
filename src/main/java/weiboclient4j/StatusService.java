package weiboclient4j;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import weiboclient4j.model.Count;
import weiboclient4j.model.Emotion;
import weiboclient4j.model.IdResponse;
import weiboclient4j.model.MidResponse;
import weiboclient4j.model.RepostTimeline;
import weiboclient4j.model.Status;
import weiboclient4j.model.Timeline;
import weiboclient4j.model.TimelineIds;
import weiboclient4j.params.BaseApp;
import weiboclient4j.params.Feature;
import weiboclient4j.params.FilterByAuthor;
import weiboclient4j.params.FilterBySource;
import weiboclient4j.params.FilterByType;
import weiboclient4j.params.Id;
import weiboclient4j.params.InboxType;
import weiboclient4j.params.IsBase62;
import weiboclient4j.params.IsBatch;
import weiboclient4j.params.IsComment;
import weiboclient4j.params.Latitude;
import weiboclient4j.params.Longitude;
import weiboclient4j.params.Mid;
import weiboclient4j.params.MidType;
import weiboclient4j.params.Paging;
import weiboclient4j.params.ScreenName;
import weiboclient4j.params.StatusParam;
import weiboclient4j.params.TrimUser;
import weiboclient4j.params.Uid;

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
public class StatusService extends AbstractService {

    public StatusService(WeiboClient2 client) {
        super(client);
    }

    public Timeline getPublicTimeline() throws WeiboClientException {
        return getPublicTimeline(Paging.EMPTY);
    }

    public Timeline getPublicTimeline(Paging paging) throws WeiboClientException {
        return getPublicTimeline(paging, BaseApp.No);
    }

    public Timeline getPublicTimeline(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return doGet("statuses/public_timeline",
                paging, withParams(baseApp), Timeline.class);
    }

    public Timeline getFriendsTimeline() throws WeiboClientException {
        return getFriendsTimeline(Paging.EMPTY);
    }

    public Timeline getFriendsTimeline(Paging paging) throws WeiboClientException {
        return getFriendsTimeline(paging, BaseApp.No, Feature.All);
    }

    public Timeline getFriendsTimeline(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return getFriendsTimeline(paging, baseApp, Feature.All);
    }

    public Timeline getFriendsTimeline(Paging paging, Feature feature) throws WeiboClientException {
        return getFriendsTimeline(paging, BaseApp.No, feature);
    }

    public Timeline getFriendsTimeline(Paging paging, TrimUser trimUser) throws WeiboClientException {
        return getFriendsTimeline(paging, BaseApp.No, trimUser);
    }

    public Timeline getFriendsTimeline(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        return getFriendsTimeline(paging, baseApp, feature, TrimUser.No);
    }

    public Timeline getFriendsTimeline(Paging paging, BaseApp baseApp, TrimUser trimUser) throws WeiboClientException {
        return getFriendsTimeline(paging, baseApp, Feature.All, trimUser);
    }

    public Timeline getFriendsTimeline(Paging paging, Feature feature, TrimUser trimUser) throws WeiboClientException {
        return getFriendsTimeline(paging, BaseApp.No, feature, trimUser);
    }

    public Timeline getFriendsTimeline(Paging paging, BaseApp baseApp, Feature feature, TrimUser trimUser) throws WeiboClientException {
        return doGet("statuses/friends_timeline",
                paging, withParams(baseApp, feature, trimUser), Timeline.class);
    }

    public Timeline getHomeTimeline() throws WeiboClientException {
        return getHomeTimeline(Paging.EMPTY);
    }

    public Timeline getHomeTimeline(Paging paging) throws WeiboClientException {
        return getHomeTimeline(paging, BaseApp.No, Feature.All);
    }

    public Timeline getHomeTimeline(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return getHomeTimeline(paging, baseApp, Feature.All);
    }

    public Timeline getHomeTimeline(Paging paging, Feature feature) throws WeiboClientException {
        return getHomeTimeline(paging, BaseApp.No, feature);
    }

    public Timeline getHomeTimeline(Paging paging, TrimUser trimUser) throws WeiboClientException {
        return getHomeTimeline(paging, BaseApp.No, trimUser);
    }

    public Timeline getHomeTimeline(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        return getHomeTimeline(paging, baseApp, feature, TrimUser.No);
    }

    public Timeline getHomeTimeline(Paging paging, BaseApp baseApp, TrimUser trimUser) throws WeiboClientException {
        return getHomeTimeline(paging, baseApp, Feature.All, trimUser);
    }

    public Timeline getHomeTimeline(Paging paging, Feature feature, TrimUser trimUser) throws WeiboClientException {
        return getHomeTimeline(paging, BaseApp.No, feature, trimUser);
    }

    public Timeline getHomeTimeline(Paging paging, BaseApp baseApp, Feature feature, TrimUser trimUser) throws WeiboClientException {
        return doGet("statuses/home_timeline",
                paging, withParams(baseApp, feature, trimUser), Timeline.class);
    }

    public TimelineIds getFriendsTimelineIds() throws WeiboClientException {
        return getFriendsTimelineIds(Paging.EMPTY);
    }

    public TimelineIds getFriendsTimelineIds(Paging paging) throws WeiboClientException {
        return getFriendsTimelineIds(paging, BaseApp.No, Feature.All);
    }

    public TimelineIds getFriendsTimelineIds(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        return doGet("statuses/friends_timeline/ids",
                paging, withParams(baseApp, feature), TimelineIds.class);
    }

    public Timeline getUserTimeline(ScreenName screenName) throws WeiboClientException {
        return getUserTimeline(screenName, Paging.EMPTY);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging) throws WeiboClientException {
        return getUserTimeline(Uid.EMPTY, screenName, paging);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging, BaseApp baseApp) throws WeiboClientException {
        return getUserTimeline(screenName, paging, baseApp, Feature.All);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging, Feature feature) throws WeiboClientException {
        return getUserTimeline(screenName, paging, BaseApp.No, feature);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging, TrimUser trimUser) throws WeiboClientException {
        return getUserTimeline(screenName, paging, BaseApp.No, trimUser);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging, Feature feature, TrimUser trimUser)
            throws WeiboClientException {
        return getUserTimeline(screenName, paging, BaseApp.No, feature, trimUser);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging, BaseApp baseApp, TrimUser trimUser)
            throws WeiboClientException {
        return getUserTimeline(screenName, paging, baseApp, Feature.All, trimUser);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging, BaseApp baseApp, Feature feature)
            throws WeiboClientException {
        return getUserTimeline(screenName, paging, baseApp, feature, TrimUser.No);
    }

    public Timeline getUserTimeline(ScreenName screenName, Paging paging, BaseApp baseApp, Feature feature, TrimUser trimUser)
            throws WeiboClientException {
        return getUserTimeline(Uid.EMPTY, screenName, paging, baseApp, feature, trimUser);
    }

    public Timeline getUserTimeline(Uid uid) throws WeiboClientException {
        return getUserTimeline(uid, Paging.EMPTY);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging) throws WeiboClientException {
        return getUserTimeline(uid, ScreenName.EMPTY, paging);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging, BaseApp baseApp) throws WeiboClientException {
        return getUserTimeline(uid, paging, baseApp, Feature.All);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging, Feature feature) throws WeiboClientException {
        return getUserTimeline(uid, paging, BaseApp.No, feature);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging, TrimUser trimUser) throws WeiboClientException {
        return getUserTimeline(uid, paging, BaseApp.No, trimUser);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging, BaseApp baseApp, Feature feature)
            throws WeiboClientException {
        return getUserTimeline(uid, paging, baseApp, feature, TrimUser.No);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging, BaseApp baseApp, TrimUser trimUser) throws WeiboClientException {
        return getUserTimeline(uid, paging, baseApp, Feature.All, trimUser);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging, Feature feature, TrimUser trimUser) throws WeiboClientException {
        return getUserTimeline(uid, paging, BaseApp.No, feature, trimUser);
    }

    public Timeline getUserTimeline(Uid uid, Paging paging, BaseApp baseApp, Feature feature, TrimUser trimUser)
            throws WeiboClientException {
        return getUserTimeline(uid, ScreenName.EMPTY, paging, baseApp, feature, trimUser);
    }

    public Timeline getUserTimeline(Uid uid, ScreenName screenName, Paging paging) throws WeiboClientException {
        return getUserTimeline(uid, screenName, paging, BaseApp.No, Feature.All, TrimUser.No);
    }

    public Timeline getUserTimeline() throws WeiboClientException {
        return getUserTimeline(Paging.EMPTY);
    }

    public Timeline getUserTimeline(TrimUser trimUser) throws WeiboClientException {
        return getUserTimeline(Paging.EMPTY, BaseApp.No, Feature.All, trimUser);
    }

    public Timeline getUserTimeline(Paging paging) throws WeiboClientException {
        return getUserTimeline(paging, BaseApp.No, Feature.All, TrimUser.No);
    }

    public Timeline getUserTimeline(Paging paging, TrimUser trimUser) throws WeiboClientException {
        return getUserTimeline(paging, BaseApp.No, Feature.All, trimUser);
    }

    public Timeline getUserTimeline(Paging paging, BaseApp baseApp, Feature feature, TrimUser trimUser)
            throws WeiboClientException {
        return getUserTimeline(Uid.EMPTY, ScreenName.EMPTY, paging, baseApp, feature, trimUser);
    }

    public Timeline getUserTimeline(Uid uid, ScreenName screenName, Paging paging, BaseApp baseApp,
                                    Feature feature, TrimUser trimUser) throws WeiboClientException {
        return doGet("statuses/user_timeline",
                paging, withParams(uid, screenName, trimUser, baseApp, feature), Timeline.class);
    }

    public TimelineIds getUserTimelineIds(ScreenName screenName) throws WeiboClientException {
        return getUserTimelineIds(screenName, Paging.EMPTY);
    }

    public TimelineIds getUserTimelineIds(ScreenName screenName, Paging paging) throws WeiboClientException {
        return getUserTimelineIds(Uid.EMPTY, screenName, paging);
    }

    public TimelineIds getUserTimelineIds(Uid uid) throws WeiboClientException {
        return getUserTimelineIds(uid, Paging.EMPTY);
    }

    public TimelineIds getUserTimelineIds(Uid uid, Paging paging) throws WeiboClientException {
        return getUserTimelineIds(uid, ScreenName.EMPTY, paging);
    }

    public TimelineIds getUserTimelineIds(Uid uid, ScreenName screenName, Paging paging) throws WeiboClientException {
        return getUserTimelineIds(uid, screenName, paging, BaseApp.No, Feature.All);
    }

    public TimelineIds getUserTimelineIds() throws WeiboClientException {
        return getUserTimelineIds(Paging.EMPTY);
    }

    public TimelineIds getUserTimelineIds(Paging paging) throws WeiboClientException {
        return getUserTimelineIds(paging, BaseApp.No, Feature.All);
    }

    public TimelineIds getUserTimelineIds(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        return getUserTimelineIds(Uid.EMPTY, ScreenName.EMPTY, paging, baseApp, feature);
    }

    public TimelineIds getUserTimelineIds(Uid uid, ScreenName screenName, Paging paging, BaseApp baseApp, Feature feature)
            throws WeiboClientException {
        return doGet("statuses/user_timeline/ids",
                paging, withParams(uid, screenName, baseApp, feature), TimelineIds.class);
    }

    public RepostTimeline getRepostTimeline(Id id) throws WeiboClientException {
        return getRepostTimeline(id, Paging.EMPTY);
    }

    public RepostTimeline getRepostTimeline(Id id, Paging paging) throws WeiboClientException {
        return getRepostTimeline(id, paging, FilterByAuthor.All);
    }

    public RepostTimeline getRepostTimeline(Id id, Paging paging, FilterByAuthor filterByAuthor) throws WeiboClientException {
        return doGet("statuses/repost_timeline",
                paging, withParams(id, filterByAuthor), RepostTimeline.class);
    }

    public TimelineIds getRepostTimelineIds(Id id) throws WeiboClientException {
        return getRepostTimelineIds(id, Paging.EMPTY);
    }

    public TimelineIds getRepostTimelineIds(Id id, Paging paging) throws WeiboClientException {
        return getRepostTimelineIds(id, paging, FilterByAuthor.All);
    }

    public TimelineIds getRepostTimelineIds(Id id, Paging paging, FilterByAuthor filterByAuthor) throws WeiboClientException {
        return doGet("statuses/repost_timeline/ids",
                paging, withParams(id, filterByAuthor), TimelineIds.class);
    }

    public RepostTimeline getRepostByMe() throws WeiboClientException {
        return getRepostByMe(Paging.EMPTY);
    }

    public RepostTimeline getRepostByMe(Paging paging) throws WeiboClientException {
        return doGet("statuses/repost_by_me", paging, RepostTimeline.class);
    }

    public Timeline getMentions() throws WeiboClientException {
        return getMentions(Paging.EMPTY);
    }

    public Timeline getMentions(Paging paging) throws WeiboClientException {
        return getMentions(paging, FilterByAuthor.All, FilterBySource.All, FilterByType.All);
    }

    public Timeline getMentions(Paging paging, FilterByAuthor filterByAuthor, FilterBySource filterBySource, FilterByType filterByType)
            throws WeiboClientException {
        return doGet("statuses/mentions",
                paging, withParams(filterByAuthor, filterBySource, filterByType), Timeline.class);
    }

    public TimelineIds getMentionsIds() throws WeiboClientException {
        return getMentionsIds(Paging.EMPTY);
    }

    public TimelineIds getMentionsIds(Paging paging) throws WeiboClientException {
        return getMentionsIds(paging, FilterByAuthor.All, FilterBySource.All, FilterByType.All);
    }

    public TimelineIds getMentionsIds(Paging paging, FilterByAuthor filterByAuthor, FilterBySource filterBySource,
                                      FilterByType filterByType) throws WeiboClientException {
        return doGet("statuses/mentions/ids",
                paging, withParams(filterByAuthor, filterBySource, filterByType), TimelineIds.class);
    }

    public Timeline getBilateralTimeline() throws WeiboClientException {
        return getBilateralTimeline(Paging.EMPTY);
    }

    public Timeline getBilateralTimeline(Paging paging) throws WeiboClientException {
        return getBilateralTimeline(paging, BaseApp.No, Feature.All);
    }

    public Timeline getBilateralTimeline(Paging paging, BaseApp baseApp, Feature feature) throws WeiboClientException {
        return doGet("statuses/bilateral_timeline",
                paging, withParams(baseApp, feature), Timeline.class);
    }

    public Status showStatus(Id id) throws WeiboClientException {
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
                withParams(Id.idParam(idList), midType, IsBase62.Yes), ArrayNode.class);

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

    public long queryId(Mid mid, MidType type, IsBase62 isBase62) throws WeiboClientException {
        return queryId(mid, type, null, isBase62);
    }

    public long queryId(Mid mid, MidType type, InboxType inboxType, IsBase62 isBase62) throws WeiboClientException {
        IdResponse idResponse = doGet("statuses/queryid",
                withParams(mid, type, inboxType, isBase62), IdResponse.class);

        return idResponse.getId();
    }

    public Map<String, Long> queryIdList(Collection<Mid> midList, MidType type, IsBase62 isBase62) throws WeiboClientException {
        return queryIdList(midList, type, null, isBase62);
    }

    public Map<String, Long> queryIdList(Collection<Mid> midList, MidType type, InboxType inboxType, IsBase62 isBase62)
            throws WeiboClientException {
        // [{"yfcLPlKKn":"3436240135184587"},{"yfd9X6XAx":"3436255091659029"}]
        ArrayNode arrayNode = doGet("statuses/queryid",
                withParams(Mid.midParam(midList), type, inboxType, isBase62, IsBatch.Yes), ArrayNode.class);

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

    public List<Status> getHotRepostDaily() throws WeiboClientException {
        return getHotRepostDaily(Paging.EMPTY);
    }

    public List<Status> getHotRepostDaily(Paging paging) throws WeiboClientException {
        return getHotRepostDaily(paging, BaseApp.No);
    }

    public List<Status> getHotRepostDaily(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return doGet("statuses/hot/repost_daily",
                paging, withParams(baseApp), Status.TYPE_STATUS_LIST);
    }

    public List<Status> getHotRepostWeekly() throws WeiboClientException {
        return getHotRepostWeekly(Paging.EMPTY);
    }

    public List<Status> getHotRepostWeekly(Paging paging) throws WeiboClientException {
        return getHotRepostWeekly(paging, BaseApp.No);
    }

    public List<Status> getHotRepostWeekly(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return doGet("statuses/hot/repost_weekly",
                paging, withParams(baseApp), Status.TYPE_STATUS_LIST);
    }

    public List<Status> getHotCommentsDaily() throws WeiboClientException {
        return getHotCommentsDaily(Paging.EMPTY);
    }

    public List<Status> getHotCommentsDaily(Paging paging) throws WeiboClientException {
        return getHotCommentsDaily(paging, BaseApp.No);
    }

    public List<Status> getHotCommentsDaily(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return doGet("statuses/hot/comments_daily",
                paging, withParams(baseApp), Status.TYPE_STATUS_LIST);
    }

    public List<Status> getHotCommentsWeekly() throws WeiboClientException {
        return getHotCommentsWeekly(Paging.EMPTY);
    }

    public List<Status> getHotCommentsWeekly(Paging paging) throws WeiboClientException {
        return getHotCommentsWeekly(paging, BaseApp.No);
    }

    public List<Status> getHotCommentsWeekly(Paging paging, BaseApp baseApp) throws WeiboClientException {
        return doGet("statuses/hot/comments_weekly",
                paging, withParams(baseApp), Status.TYPE_STATUS_LIST);
    }

    public List<Count> getStatusesCounts(Collection<Id> ids) throws WeiboClientException {
        return doGet("statuses/count",
                withParams(Id.idsParam(ids)), Count.TYPE_COUNT_LIST);
    }

    public Status repostStatus(Id id, String status) throws WeiboClientException {
        return repostStatus(id, status, IsComment.No);
    }

    public Status repostStatus(Id id, String status, IsComment isComment) throws WeiboClientException {
        return repostStatus(id, new StatusParam(status), isComment);
    }

    public Status repostStatus(Id id, StatusParam status, IsComment isComment) throws WeiboClientException {
        return doPost("statuses/repost", withParams(id, status, isComment), Status.class);
    }

    public Status updateStatus(String status) throws WeiboClientException {
        return updateStatus(status, null, null);
    }

    public Status updateStatus(String status, Latitude latitude, Longitude longitude) throws WeiboClientException {
        return updateStatus(new StatusParam(status), latitude, longitude);
    }

    public Status updateStatus(StatusParam status, Latitude latitude, Longitude longitude) throws WeiboClientException {
        return doPost("statuses/update", withParams(status, latitude, longitude), Status.class);
    }

    public Status destroyStatus(Id id) throws WeiboClientException {
        return doPost("statuses/destroy", withParams(id), Status.class);
    }

    public Status uploadImageUrl(String status, URL url) throws WeiboClientException {
        return uploadImageUrl(status, url, null, null);
    }

    public Status uploadImageUrl(String status, URL url, Latitude latitude, Longitude longitude) throws WeiboClientException {
        return uploadImageUrl(new StatusParam(status), url, latitude, longitude);
    }

    public Status uploadImageUrl(StatusParam status, URL url, Latitude latitude, Longitude longitude) throws WeiboClientException {
        return doPost("statuses/upload_url_text",
                withParams(status, urlParam(url), latitude, longitude), Status.class);
    }

//    TODO implements update binary image
//    public Status uploadImageBinary() {
//
//    }

    public List<Emotion> getEmotions() throws WeiboClientException {
        return doGet("emotions", Emotion.TYPE_EMOTION_LIST);
    }
}
