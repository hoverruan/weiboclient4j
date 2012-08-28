package weiboclient4j.examples;

import weiboclient4j.AccountService;
import weiboclient4j.CommentService;
import weiboclient4j.StatusService;
import weiboclient4j.WeiboClient2;
import weiboclient4j.model.Comment;
import weiboclient4j.model.CommentList;
import weiboclient4j.model.Emotion;
import weiboclient4j.model.Status;
import weiboclient4j.model.Timeline;
import weiboclient4j.model.TimelineIds;
import weiboclient4j.oauth2.DisplayType;
import weiboclient4j.oauth2.GrantType;
import weiboclient4j.oauth2.ResponseType;
import weiboclient4j.oauth2.SinaWeibo2AccessToken;
import weiboclient4j.params.BaseApp;
import weiboclient4j.params.Cid;
import weiboclient4j.params.Feature;
import weiboclient4j.params.Id;
import weiboclient4j.params.IsBase62;
import weiboclient4j.params.Mid;
import weiboclient4j.params.MidType;
import weiboclient4j.params.Paging;
import weiboclient4j.params.ScreenName;
import weiboclient4j.params.TrimUser;
import weiboclient4j.params.Uid;
import static weiboclient4j.utils.JsonUtils.writeObjectAsString;
import static weiboclient4j.utils.StringUtils.isBlank;
import static weiboclient4j.utils.StringUtils.isNotBlank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

/**
 * @author Hover Ruan
 */
public class OAuth2CommandLine {

    public static final String API_KEY = "api_key";
    public static final String API_SECRET = "api_secret";

    public static void main(String[] args) throws Exception {
        Preferences pref = Preferences.userRoot().node("/weiboclient4j/example/oauth2");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String savedKey = pref.get(API_KEY, "");
        String defaultKey = isBlank(savedKey) ? "" : " [" + savedKey + "] ";
        System.out.print("Input client id (api key)" + defaultKey + ": ");
        String apiKey = in.readLine();
        if (isNotBlank(apiKey)) {
            pref.put(API_KEY, apiKey);
        } else if (isNotBlank(savedKey)) {
            apiKey = savedKey;
        }

        String savedSecret = pref.get(API_SECRET, "");
        String defaultSecret = isBlank(savedSecret) ? "" : " [" + savedSecret + "] ";
        System.out.print("Input client secret (api secret)" + defaultSecret + ": ");
        String apiSecret = in.readLine();
        if (isNotBlank(apiSecret)) {
            pref.put(API_SECRET, apiSecret);
        } else if (isNotBlank(savedSecret)) {
            apiSecret = savedSecret;
        }

        String authorizationCallback = "http://demo.localhost.weiboclient4j.org/callback";
        WeiboClient2 client = new WeiboClient2(apiKey, apiSecret);
        String state = "__MY_STATE__";
        String url = client.getAuthorizationUrl(ResponseType.Code, DisplayType.Default, state, authorizationCallback);
        System.out.println("Please visit: " + url);

        System.out.print("Input code: ");
        String code = in.readLine();
        String accessTokenCallback = "http://demo.localhost.weiboclient4j.org/callback";
        SinaWeibo2AccessToken accessToken = client.getAccessToken(GrantType.AuthorizationCode, code, accessTokenCallback);
        System.out.println();
        System.out.println("Access token: " + accessToken.getToken());
        System.out.println("Uid: " + accessToken.getUid());
        System.out.println("Expires in: " + accessToken.getExpiresIn());
        System.out.println("Remind in: " + accessToken.getRemindIn());

        accessToken = new SinaWeibo2AccessToken(accessToken.getToken());
        client.setAccessToken(accessToken);
        AccountService accountService = client.getAccountService();
        long uid = accountService.getAccountUid();
        System.out.println();
        System.out.println("Got account uid: " + uid);

        StatusService statusService = client.getStatusService();
        Timeline publicTimeline = statusService.getPublicTimeline();
        System.out.println();
        System.out.println("Public timeline: " + writeObjectAsString(publicTimeline));

        Timeline friendsTimeline = statusService.getFriendsTimeline();
        System.out.println();
        System.out.println("Friends timeline: " + writeObjectAsString(friendsTimeline));

        Timeline homeTimeline = statusService.getHomeTimeline();
        System.out.println();
        System.out.println("Home timeline: " + writeObjectAsString(homeTimeline));

        TimelineIds friendsTimelineIds = statusService.getFriendsTimelineIds();
        System.out.println();
        System.out.println("Friends timeline ids: " + writeObjectAsString(friendsTimelineIds));

        Timeline userTimeline = statusService.getUserTimeline();
        System.out.println();
        System.out.println("User timeline: " + writeObjectAsString(userTimeline));

        Timeline userTimelineTrimUser = statusService.getUserTimeline(TrimUser.No);
        System.out.println();
        System.out.println("User timeline that trim user: " + writeObjectAsString(userTimelineTrimUser));

        statusService.getUserTimeline(ScreenName.EMPTY);
        statusService.getUserTimeline(Uid.EMPTY);
        statusService.getUserTimeline(Paging.EMPTY, TrimUser.Yes);

        Timeline userTimelineFor1834561765 = statusService.getUserTimeline(new Uid(1834561765L), null, null, BaseApp.No, Feature.All, TrimUser.No);
        System.out.println();
        System.out.println("User timeline for 1834561765: " + writeObjectAsString(userTimelineFor1834561765));

        statusService.getUserTimelineIds(ScreenName.EMPTY);
        statusService.getUserTimelineIds(Uid.EMPTY);
        statusService.getUserTimelineIds();

        statusService.getRepostTimeline(new Id(3436240135184587L));
        statusService.getRepostTimelineIds(new Id(3436240135184587L));

        statusService.getRepostByMe();

        statusService.getMentions();
        statusService.getMentionsIds();

        statusService.getBilateralTimeline();

        statusService.showStatus(new Id(3436240135184587L));

        statusService.queryMid(new Id(3436240135184587L), MidType.Status);

        List<Id> idList = new ArrayList<Id>(2);
        idList.add(new Id(3436240135184587L));
        idList.add(new Id(3436255091659029L));
        Map<Long, String> midMap = statusService.queryMidList(idList, MidType.Status);
        System.out.println();
        System.out.println("Mid " + 3436240135184587L + "=" + midMap.get(3436240135184587L) + ", " +
                3436255091659029L + "=" + midMap.get(3436255091659029L));

        statusService.queryId(new Mid("yfcLPlKKn"), MidType.Message, IsBase62.Yes);

        List<Mid> midList = new ArrayList<Mid>(2);
        midList.add(new Mid("yfcLPlKKn"));
        midList.add(new Mid("yfd9X6XAx"));

        Map<String, Long> idMap = statusService.queryIdList(midList, MidType.Message, IsBase62.Yes);
        System.out.println();
        System.out.println("Id yfcLPlKKn=" + idMap.get("yfcLPlKKn") + ", yfd9X6XAx=" + idMap.get("yfd9X6XAx"));

        List<Status> hotRepostDaily = statusService.getHotRepostDaily();
        System.out.println();
        System.out.println("Hot repost daily: " + writeObjectAsString(hotRepostDaily));

        List<Status> hotRepostWeekly = statusService.getHotRepostWeekly();
        System.out.println();
        System.out.println("Hot report weekly: " + writeObjectAsString(hotRepostWeekly));

        statusService.getHotCommentsDaily();
        statusService.getHotCommentsWeekly();

        statusService.getStatusesCounts(idList);

        Status justPostStatus = statusService.updateStatus("Update status api test");
        Status repostStatus = statusService.repostStatus(new Id(justPostStatus.getId()), "Repost test");
        System.out.println();
        System.out.println("Just post: " + writeObjectAsString(justPostStatus));
        System.out.println("Repost: " + writeObjectAsString(repostStatus));

        statusService.destroyStatus(new Id(repostStatus.getId()));

        // Need advanced permission
        Status uploadedStatusByImageUrl = statusService.uploadImageUrl("Post image test",
                new URL("https://a248.e.akamai.net/assets.github.com/images/modules/about_page/octocat.png?1306884373"));
        statusService.destroyStatus(new Id(uploadedStatusByImageUrl.getId()));

        List<Emotion> emotions = statusService.getEmotions();
        System.out.println();
        System.out.println("Emotions: " + writeObjectAsString(emotions));

        CommentService commentService = client.getCommentService();
        commentService.getComments(new Id(3436240135184587L));
        CommentList commentsByMe = commentService.getCommentsByMe();
        long firstCommentId = commentsByMe.getComments().get(0).getId();

        commentService.getCommentsToMe();
        commentService.getCommentsTimeline();
        commentService.getMentionsComments();

        ArrayList<Cid> batchCids = new ArrayList<Cid>();
        batchCids.add(new Cid(firstCommentId));
        commentService.getCommentsBatch(batchCids);

        Comment comment = commentService.createComment(new Id(justPostStatus.getId()), "Create comment test");
        Comment reply = commentService.replyComment(new Id(justPostStatus.getCommentsCount()), new Cid(comment.getId()), "Reply test");
        commentService.destroyComment(new Cid(comment.getId()));
        commentService.destroyComment(new Cid(reply.getId()));

        statusService.destroyStatus(new Id(justPostStatus.getId()));
        commentService.destroyCommentBatch(new ArrayList<Cid>());
    }
}
