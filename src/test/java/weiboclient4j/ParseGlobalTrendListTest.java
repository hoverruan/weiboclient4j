package weiboclient4j;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;
import weiboclient4j.model.GlobalTrendList;
import static weiboclient4j.utils.JsonUtils.readValue;

/**
 * @author Hover Ruan
 */
public class ParseGlobalTrendListTest extends AbstractParseJsonTest {
    @Test
    public void testParseGlobalTrendList() throws Exception {
        String content = readResource("/weiboclient4j/global_trend_list.json");

        new GlobalTrendList(readValue(content, JsonNode.class));
    }
}
