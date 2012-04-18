package weiboclient4j.model;

import org.junit.Test;
import weiboclient4j.AbstractParseJsonTest;
import weiboclient4j.WeiboClient;
import weiboclient4j.utils.JsonUtils;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;

/**
 * @author Hover Ruan
 */
public class ParseStatusTest extends AbstractParseJsonTest {
    @Test
    public void testParseStatusList() throws Exception {
        String content = readResource("/weiboclient4j/model/statuses.json");
        JsonUtils.parseJsonObject(content, WeiboClient.TYPE_STATUS_LIST);
    }

    @Test
    public void testParseStatusesIncludingDeleted() throws Exception {
        String content = readResource("/weiboclient4j/model/statuses_including_deleted.json");
        parseJsonObject(content, WeiboClient.TYPE_STATUS_LIST);
    }
}
