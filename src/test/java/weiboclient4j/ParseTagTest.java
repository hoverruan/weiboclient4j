package weiboclient4j;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;

/**
 * @author Hover Ruan
 */
public class ParseTagTest extends AbstractParseJsonTest {

    @Test
    public void testParseTests() throws Exception {
        String content = readResource("/weiboclient4j/tags.json");

        Tag.parseTags(client.mapper.readValue(content, JsonNode.class));
    }
}
