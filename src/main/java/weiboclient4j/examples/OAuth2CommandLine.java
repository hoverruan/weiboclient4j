package weiboclient4j.examples;

import static java.util.Arrays.asList;
import weiboclient4j.AccountService;
import weiboclient4j.CommentService;
import weiboclient4j.StatusService;
import weiboclient4j.WeiboClient;
import weiboclient4j.WeiboClientException;
import weiboclient4j.model.Comment;
import weiboclient4j.model.CommentList;
import weiboclient4j.model.Status;
import weiboclient4j.model.Timeline;
import weiboclient4j.model.TimelineIds;
import weiboclient4j.oauth2.DisplayType;
import weiboclient4j.oauth2.ResponseType;
import weiboclient4j.oauth2.SinaWeibo2AccessToken;
import weiboclient4j.params.BaseApp;
import weiboclient4j.params.Cid;
import static weiboclient4j.params.CoreParameters.cid;
import static weiboclient4j.params.CoreParameters.id;
import static weiboclient4j.params.CoreParameters.mid;
import static weiboclient4j.params.CoreParameters.uid;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

/**
 * @author Hover Ruan
 */
//
public class OAuth2CommandLine {

    public static final String API_KEY = "api_key";

    public static final String API_SECRET = "api_secret";

    private static final long STATUS_ID = 3436240135184587L;

    private static final long ANOTHER_STATUS_ID = 3436255091659029L;

    private static final long UID = 1834561765L;

    private WeiboClient client;

    private BufferedReader in;

    private Status justPostStatus;

    public OAuth2CommandLine(BufferedReader in) {
        this.in = in;
    }

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

        WeiboClient client = new WeiboClient(apiKey, apiSecret);

        OAuth2CommandLine cmd = new OAuth2CommandLine(in);
        cmd.setClient(client);

        cmd.retrieveAccessToken();
        cmd.retrieveAccountUid();
        cmd.retrievePublicTimeline();
        cmd.retrieveFriendsTimeline();
        cmd.retrieveHomeTimeline();
        cmd.retrieveUserTimeline();
        cmd.retrieveOtherTimeline();
        cmd.retrieveIds();
        cmd.updateStatus();
        cmd.retrieveAndUpdateComments();
    }

    public void setClient(WeiboClient client) {
        this.client = client;
    }

    private void retrieveAndUpdateComments() throws WeiboClientException, IOException {
        StatusService statusService = client.getStatusService();

        CommentService commentService = client.getCommentService();
        commentService.getComments(id(STATUS_ID));
        CommentList commentsByMe = commentService.getCommentsByMe();
        long firstCommentId = commentsByMe.getComments().get(0).getId();

        commentService.getCommentsToMe();
        commentService.getCommentsTimeline();
        commentService.getMentionsComments();

        List<Cid> batchCids = asList(cid(firstCommentId));
        commentService.getCommentsBatch(batchCids);

        Comment comment = commentService.createComment(id(justPostStatus.getId()), "Create comment test");
        Comment reply = commentService.replyComment(
                id(justPostStatus.getId()),
                cid(comment.getId()),
                "Reply test");
        commentService.destroyComment(cid(comment.getId()));
        commentService.destroyComment(cid(reply.getId()));

        statusService.destroy(id(justPostStatus.getId()));
    }

    private void updateStatus() throws WeiboClientException, IOException {
        StatusService statusService = client.getStatusService();

        justPostStatus = statusService.update("Update status api test");
        Status repostStatus = statusService.repost(id(justPostStatus.getId()), "Repost test");
        System.out.println();
        System.out.println("Just post: " + writeObjectAsString(justPostStatus));
        System.out.println("Repost: " + writeObjectAsString(repostStatus));

        statusService.destroy(id(repostStatus.getId()));

        // Need advanced permission
//        Status uploadedStatusByImageUrl = statusService.uploadImageUrl("Post image test",
//                new URL("https://a248.e.akamai.net/assets.github.com/images/modules/about_page/octocat.png?1306884373")
//        );
//        statusService.destroy(id(uploadedStatusByImageUrl.getId()));
    }

    private void retrieveIds() throws WeiboClientException {
        StatusService statusService = client.getStatusService();

        statusService.show(id(STATUS_ID));

        statusService.queryMid(id(STATUS_ID), MidType.Status);

        List<Id> idList = asList(id(STATUS_ID), id(ANOTHER_STATUS_ID));
        Map<Long, String> midMap = statusService.queryMidList(idList, MidType.Status);
        System.out.println();
        System.out.println("Mid " + STATUS_ID + "=" + midMap.get(STATUS_ID) + ", "
                + ANOTHER_STATUS_ID + "=" + midMap.get(ANOTHER_STATUS_ID));

        statusService.queryId(mid("yfcLPlKKn"), MidType.Message, IsBase62.Yes);

        List<Mid> midList = asList(mid("yfcLPlKKn"), mid("yfd9X6XAx"));

        Map<String, Long> idMap = statusService.queryIdList(midList, MidType.Message, IsBase62.Yes);
        System.out.println();
        System.out.println("Id yfcLPlKKn=" + idMap.get("yfcLPlKKn") + ", yfd9X6XAx=" + idMap.get("yfd9X6XAx"));

        statusService.getStatusesCounts(idList);
    }

    private void retrieveOtherTimeline() throws WeiboClientException {
        StatusService statusService = client.getStatusService();

        statusService.getRepostTimeline(id(STATUS_ID));
        statusService.getRepostTimelineIds(id(STATUS_ID));

        statusService.getRepostByMe();

        statusService.getMentions();
        statusService.getMentionsIds();

        statusService.getBilateralTimeline();
    }

    private void retrieveUserTimeline() throws WeiboClientException, IOException {
        StatusService statusService = client.getStatusService();

        Timeline userTimeline = statusService.getUserTimeline();
        System.out.println();
        System.out.println("User timeline: " + writeObjectAsString(userTimeline));

        Timeline userTimelineTrimUser = statusService.getUserTimeline(TrimUser.No);
        System.out.println();
        System.out.println("User timeline that trim user: " + writeObjectAsString(userTimelineTrimUser));

        statusService.getUserTimeline(ScreenName.EMPTY);
        statusService.getUserTimeline(Uid.EMPTY);
        statusService.getUserTimeline(Paging.EMPTY, TrimUser.Yes);

        Timeline userTimelineFor1834561765 = statusService.getUserTimeline(
                uid(UID), BaseApp.No, Feature.All, TrimUser.No);
        System.out.println();
        System.out.println("User timeline for 1834561765: " + writeObjectAsString(userTimelineFor1834561765));

        statusService.getUserTimelineIds(ScreenName.EMPTY);
        statusService.getUserTimelineIds(Uid.EMPTY);
        statusService.getUserTimelineIds();
    }

    private void retrieveHomeTimeline() throws IOException, WeiboClientException {
        StatusService statusService = client.getStatusService();

        Timeline homeTimeline = statusService.getHomeTimeline();
        System.out.println();
        System.out.println("Home timeline: " + writeObjectAsString(homeTimeline));
    }

    private void retrieveFriendsTimeline() throws WeiboClientException, IOException {
        StatusService statusService = client.getStatusService();

        Timeline friendsTimeline = statusService.getFriendsTimeline();
        System.out.println();
        System.out.println("Friends timeline: " + writeObjectAsString(friendsTimeline));

        TimelineIds friendsTimelineIds = statusService.getFriendsTimelineIds();
        System.out.println();
        System.out.println("Friends timeline ids: " + writeObjectAsString(friendsTimelineIds));
    }

    private void retrievePublicTimeline() throws WeiboClientException, IOException {
        StatusService statusService = client.getStatusService();

        Timeline publicTimeline = statusService.getPublicTimeline();
        System.out.println();
        System.out.println("Public timeline: " + writeObjectAsString(publicTimeline));
    }

    private void retrieveAccountUid() throws WeiboClientException {
        AccountService accountService = client.getAccountService();
        long uid = accountService.getUid();

        System.out.println();
        System.out.println("Got account uid: " + uid);
    }

    private void retrieveAccessToken() throws IOException {
        String state = "__MY_STATE__";
        String authorizationCallback = "http://demo.localhost.weiboclient4j.org/callback";
        String url = client.getAuthorizationUrl(ResponseType.Code, DisplayType.Default, state, authorizationCallback);
        System.out.println("Please visit: " + url);

        System.out.print("Input code: ");
        String code = in.readLine();
        String accessTokenCallback = "http://demo.localhost.weiboclient4j.org/callback";
        SinaWeibo2AccessToken accessToken = client.getAccessTokenByCode(code, accessTokenCallback);
        System.out.println();
        System.out.println("Access token: " + accessToken.getToken());
        System.out.println("Uid: " + accessToken.getUid());
        System.out.println("Expires in: " + accessToken.getExpiresIn());
        System.out.println("Remind in: " + accessToken.getRemindIn());

        accessToken = new SinaWeibo2AccessToken(accessToken.getToken());
        client.setAccessToken(accessToken);
    }
}
