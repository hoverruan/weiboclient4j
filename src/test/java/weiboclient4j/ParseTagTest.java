package weiboclient4j;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;
import static weiboclient4j.utils.JsonUtils.readValue;

/**
 * @author Hover Ruan
 */
public class ParseTagTest extends AbstractParseJsonTest {

    @Test
    public void testParseTests() throws Exception {
        String content = readResource("/weiboclient4j/tags.json");

        Tag.parseTags(readValue(content, JsonNode.class));
    }
}
