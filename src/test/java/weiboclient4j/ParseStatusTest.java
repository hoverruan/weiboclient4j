package weiboclient4j;

import org.junit.Test;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;

/**
 * @author Hover Ruan
 */
public class ParseStatusTest extends AbstractParseJsonTest {
    @Test
    public void testParseStatusList() throws Exception {
        String content = readResource("/weiboclient4j/statuses.json");
        parseJsonObject(content, WeiboClient.TYPE_STATUS_LIST);
    }

    @Test
    public void testParseStatusesIncludingDeleted() throws Exception {
        String content = readResource("/weiboclient4j/statuses_including_deleted.json");
        parseJsonObject(content, WeiboClient.TYPE_STATUS_LIST);
    }
}
