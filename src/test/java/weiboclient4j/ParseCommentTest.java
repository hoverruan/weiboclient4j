package weiboclient4j;

import org.junit.Test;

/**
 * @author Hover Ruan
 */
public class ParseCommentTest extends AbstractParseJsonTest {

    @Test
    public void testParseComments() throws Exception {
        String content = readResource("/weiboclient4j/comments.json");

        client.parseJsonObject(content, WeiboClient.TYPE_COMMENT_LIST);
    }
}
