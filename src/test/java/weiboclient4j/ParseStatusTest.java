package weiboclient4j;

import org.junit.Test;

/**
 * @author Hover Ruan
 */
public class ParseStatusTest extends AbstractParseJsonTest {
    @Test
    public void testParseStatusList() throws Exception {
        String content = readResource("/weiboclient4j/statuses.json");
        client.parseJsonObject(content, WeiboClient.TYPE_STATUS_LIST);
    }

    @Test
    public void testParseStatusesIncludingDeleted() throws Exception {
        String content = readResource("/weiboclient4j/statuses_including_deleted.json");
        client.parseJsonObject(content, WeiboClient.TYPE_STATUS_LIST);
    }
}
