package weiboclient4j;

import org.junit.Test;
import weiboclient4j.model.Comment;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;

/**
 * @author Hover Ruan
 */
public class ParseCommentTest extends AbstractParseJsonTest {

    @Test
    public void testParseComments() throws Exception {
        String content = readResource("/weiboclient4j/comments.json");

        parseJsonObject(content, Comment.TYPE_COMMENT_LIST);
    }
}
