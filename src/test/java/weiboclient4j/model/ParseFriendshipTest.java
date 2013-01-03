package weiboclient4j.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import weiboclient4j.utils.JsonUtils;

/**
 * @author Hover Ruan
 */
public class ParseFriendshipTest extends AbstractParseJsonTest {
    @Test
    public void testParseFriendship() throws Exception {
        String content = readResource("/weiboclient4j/model/friendship.json");
        Friendship friendship = JsonUtils.readValue(content, Friendship.class);
        assertNotNull(friendship);
        assertTrue(friendship.getTarget().isFollowing());
    }
}
