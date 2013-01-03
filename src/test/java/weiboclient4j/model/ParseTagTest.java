package weiboclient4j.model;

import org.codehaus.jackson.JsonNode;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static weiboclient4j.utils.JsonUtils.readValue;

import java.util.List;

/**
 * @author Hover Ruan
 */
public class ParseTagTest extends AbstractParseJsonTest {

    @Test
    public void testParseTests() throws Exception {
        String content = readResource("/weiboclient4j/model/tags.json");

        List<Tag> tags = Tag.parseTags(readValue(content, JsonNode.class));
        assertNotNull(tags);

        Tag tag = tags.get(0);
        assertTrue(tag.getId() > 0);
        assertTrue(tag.getWeight() > 0);
    }
}
