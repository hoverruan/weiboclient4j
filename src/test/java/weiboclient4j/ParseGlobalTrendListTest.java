package weiboclient4j;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;

/**
 * @author Hover Ruan
 */
public class ParseGlobalTrendListTest extends AbstractParseJsonTest {
    @Test
    public void testParseGlobalTrendList() throws Exception {
        String content = readResource("/weiboclient4j/global_trend_list.json");

        new GlobalTrendList(client.mapper.readValue(content, JsonNode.class));
    }
}
