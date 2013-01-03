package weiboclient4j.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;

/**
 * @author Hover Ruan
 */
public class ParseCommentListTest extends AbstractParseJsonTest {

    @Test
    public void testParseComments() throws Exception {
        String content = readResource("/weiboclient4j/model/comment_list.json");

        CommentList commentList = parseJsonObject(content, CommentList.class);
        assertThat(commentList.getTotalNumber(), is(53));
        assertThat(commentList.getComments(), is(notNullValue()));
        assertThat(commentList.getComments().size(), is(46));
    }
}
