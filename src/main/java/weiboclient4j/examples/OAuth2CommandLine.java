package weiboclient4j.examples;

import weiboclient4j.WeiboClient2;
import weiboclient4j.model.Timeline;
import weiboclient4j.model.TimelineIds;
import weiboclient4j.oauth2.DisplayType;
import weiboclient4j.oauth2.GrantType;
import weiboclient4j.oauth2.ResponseType;
import weiboclient4j.oauth2.SinaWeibo2AccessToken;
import weiboclient4j.utils.JsonUtils;
import static weiboclient4j.utils.JsonUtils.writeObjectAsString;
import static weiboclient4j.utils.StringUtils.isBlank;
import static weiboclient4j.utils.StringUtils.isNotBlank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        SinaWeibo2AccessToken accessToken = client.getAccessToken(GrantType.AuthorizationCode, code, state, accessTokenCallback);
        System.out.println();
        System.out.println("Access token: " + accessToken.getToken());
        System.out.println("Uid: " + accessToken.getUid());
        System.out.println("Expires in: " + accessToken.getExpiresIn());
        System.out.println("Remind in: " + accessToken.getRemindIn());

        accessToken = new SinaWeibo2AccessToken(accessToken.getToken(), "");
        client.setAccessToken(accessToken);
        long uid = client.getAccountUid();
        System.out.println();
        System.out.println("Got account uid: " + uid);

        Timeline publicTimeline = client.getPublicTimeline();
        System.out.println();
        System.out.println("Public timeline: " + writeObjectAsString(publicTimeline));

        Timeline friendsTimeline = client.getFriendsTimeline();
        System.out.println();
        System.out.println("Friends timeline: " + writeObjectAsString(friendsTimeline));

        Timeline homeTimeline = client.getHomeTimeline();
        System.out.println();
        System.out.println("Home timeline: " + writeObjectAsString(homeTimeline));

        TimelineIds friendsTimelineIds = client.getFriendsTimelineIds();
        System.out.println();
        System.out.println("Friends timeline ids: " + writeObjectAsString(friendsTimelineIds));

        Timeline userTimeline = client.getUserTimeline();
        System.out.println();
        System.out.println("User timeline: " + writeObjectAsString(userTimeline));

        Timeline userTimelineTrimUser = client.getUserTimeline(true);

        Timeline userTimelineFor1834561765 = client.getUserTimeline(1834561765L);
        System.out.println();
        System.out.println("User timeline for 1834561765: " + writeObjectAsString(userTimelineFor1834561765));
    }
}
