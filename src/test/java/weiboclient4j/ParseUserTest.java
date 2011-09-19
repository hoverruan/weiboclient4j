package weiboclient4j;

import org.junit.Test;

/**
 * @author Hover Ruan
 */
public class ParseUserTest extends AbstractParseJsonTest {

    @Test
    public void testParseUser() throws Exception {
        String content = readResource("/weiboclient4j/users.json");
        client.parseJsonObject(content, User.class);
    }

    @Test
    public void testParseUserList() throws Exception {
        String content = readResource("/weiboclient4j/user_list.json");
        client.parseJsonObject(content, WeiboClient.TYPE_USER_LIST);
    }
}
