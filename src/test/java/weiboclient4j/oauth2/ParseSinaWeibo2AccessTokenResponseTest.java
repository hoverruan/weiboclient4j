package weiboclient4j.oauth2;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import weiboclient4j.model.AbstractParseJsonTest;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;

/**
 * @author Hover Ruan
 */
public class ParseSinaWeibo2AccessTokenResponseTest extends AbstractParseJsonTest {
    @Test
    public void testParseAccessTokenResponse() throws Exception {
        String content = readResource("/weiboclient4j/oauth2/access_token_response.json");

        SinaWeibo2AccessTokenResponse response = parseJsonObject(content, SinaWeibo2AccessTokenResponse.class);
        assertEquals("AccessToken", "ACCESS_TOKEN", response.getAccessToken());
        assertEquals("ExpiresIn", 1234, response.getExpiresIn());
        assertEquals("RemindIn", 798114, response.getRemindIn());
        assertEquals("Uid", 12341234, response.getUid());
        assertEquals("isRealName", true, response.isRealName());
    }
}
