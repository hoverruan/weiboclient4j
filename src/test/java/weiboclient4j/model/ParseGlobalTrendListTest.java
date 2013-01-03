package weiboclient4j.model;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;
import static weiboclient4j.utils.JsonUtils.readValue;

/**
 * @author Hover Ruan
 */
public class ParseGlobalTrendListTest extends AbstractParseJsonTest {
    @Test
    public void testParseGlobalTrendList() throws Exception {
        String content = readResource("/weiboclient4j/model/global_trend_list.json");

        new GlobalTrendList(readValue(content, JsonNode.class));
    }
}
