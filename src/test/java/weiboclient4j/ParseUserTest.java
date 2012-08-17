package weiboclient4j;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import weiboclient4j.model.User;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;

/**
 * @author Hover Ruan
 */
public class ParseUserTest extends AbstractParseJsonTest {

    @Test
    public void testParseUser() throws Exception {
        String content = readResource("/weiboclient4j/users.json");
        User user = parseJsonObject(content, User.class);

        assertNotNull(user.getCreatedAt());
    }

    @Test
    public void testParseUser2() throws Exception {
        String content = readResource("/weiboclient4j/user2.json");
        parseJsonObject(content, User.class);
    }

    @Test
    public void testParseUserList() throws Exception {
        String content = readResource("/weiboclient4j/user_list.json");
        parseJsonObject(content, User.TYPE_USER_LIST);
    }
}
